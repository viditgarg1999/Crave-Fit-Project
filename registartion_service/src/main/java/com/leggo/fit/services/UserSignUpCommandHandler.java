package com.leggo.fit.services;

import com.leggo.fit.common.configuration.JwtConfig.JwtUtil;
import com.leggo.fit.common.configuration.JwtConfig.RefreshTokenUtil;
import com.leggo.fit.entities.*;
import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.usersignup.UserSignUpRequestModel;
import com.leggo.fit.dto.usersignup.UserSignUpResponseModel;
import com.leggo.fit.infrastructure.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserSignUpCommandHandler {

    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    @Autowired
    private UserProfileDetailsDao userProfileDetailsDao;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RefreshTokenUtil refreshTokenUtil;

    @Autowired
    private TemporaryUserAccountDetailsDao temporaryUserAccountDetailsDao;

    @Autowired
    private OtpVerificationCommandHandler otpVerification;

    @Autowired
    private UserRequestedOtpDao userRequestedOtpDao;

    @Autowired
    private UserEarningProgressDao userEarningProgressDao;

    @Autowired
    private UserActivityProgressDao userActivityProgressDao;


    public ResponseEntity<?> handleUnapprovedRequest(UserSignUpRequestModel userSignUpRequestModel)
    {

        try {
            if (userAccountDetailsDao.existsByMobileNumber(userSignUpRequestModel.getMobileNumber())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Mobile number already exists! Please try to login in");
            } else {

                // To save the user details in temporary dataset
                if (temporaryUserAccountDetailsDao.existsById(userSignUpRequestModel.getMobileNumber())) {
                    // Update the user details
                    int updatedRows = temporaryUserAccountDetailsDao.updateOtpDetails(userSignUpRequestModel.getMobileNumber(), userSignUpRequestModel.getName(), userSignUpRequestModel.getPassword());

                } else {

                    TemporaryUserAccountDetails temporaryUserAccountDetails = new TemporaryUserAccountDetails(userSignUpRequestModel.getMobileNumber(), userSignUpRequestModel.getName(), userSignUpRequestModel.getPassword(),new Date());
                    temporaryUserAccountDetailsDao.save(temporaryUserAccountDetails);
                }

                return otpVerification.generateOTP(userSignUpRequestModel.getMobileNumber());
            }
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> handle(MobileNumberRequestModel mobileNumberRequestModel)
    {
        try {

            TemporaryUserAccountDetails temporaryUserAccountDetails = temporaryUserAccountDetailsDao.getById(mobileNumberRequestModel.getMobileNumber());


            UserAccountDetails userAccountDetails = new UserAccountDetails(temporaryUserAccountDetails.getName(), temporaryUserAccountDetails.getMobileNumber(), temporaryUserAccountDetails.getPassword(), new Date());
            var res = userAccountDetailsDao.save(userAccountDetails);

            if(res==null)
            {
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Server error, please try again");
            }

            // To create Record in the UserProfile Section
            UserProfileDetails userProfileDetails = new UserProfileDetails(res.getId(), temporaryUserAccountDetails.getName(), temporaryUserAccountDetails.getMobileNumber());
            userProfileDetailsDao.save(userProfileDetails);

            // To create Record in UserActivityProgress Section
            String weeklyActivityProgress = "0,0,0,0,0,0,0";

            String frequentActivities = "Gyming,Cycling,Running,Walking,Badminton,Park Activities";

            LocalDate lastActivityDate = LocalDate.now();

            UserActivityProgress userActivityProgress = new UserActivityProgress(res.getId(),0,weeklyActivityProgress,frequentActivities,lastActivityDate);
            userActivityProgressDao.save(userActivityProgress);

            // To create Record in UserEarningProgress Section
            String weeklyEarningProgress = "0,0,0,0,0,0,0";
            UserEarningProgress userEarningProgress = new UserEarningProgress(res.getId(),weeklyEarningProgress,0,0,0);
            userEarningProgressDao.save(userEarningProgress);


            return ResponseEntity.ok(new UserSignUpResponseModel(res.getId()));
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

}
