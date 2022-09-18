package com.fitproject.cravefit.services;

import com.fitproject.cravefit.dto.GeneratedOtp;
import com.fitproject.cravefit.dto.UserValidateOtpRequestModel;
import com.fitproject.cravefit.entities.UserRequestedOtp;
import com.fitproject.cravefit.infrastructure.dao.UserAccountDetailsDao;
import com.fitproject.cravefit.infrastructure.dao.UserProfileDetailsDao;
import com.fitproject.cravefit.infrastructure.dao.UserRequestedOtpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OtpVerificationCommandHandler {


    @Autowired
    private UserRequestedOtpDao userRequestedOtpDao;

    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    @Autowired
    private UserProfileDetailsDao userProfileDetailsDao;

    public ResponseEntity<?> generateOTP(String mobileOrEmail)
    {
        try {
            String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));

            Date date = new Date();
            Timestamp currTime = new Timestamp(date.getTime());

            GeneratedOtp generatedOtp = new GeneratedOtp(otp, currTime);

            // To send email
            if(mobileOrEmail.contains("@"))
                System.out.println("Need to send otp on email");
            // To generate otp
            else
                System.out.println("Need to send otp on mobile");

            if (userRequestedOtpDao.existsById(mobileOrEmail)) {
                int updatedRows = userRequestedOtpDao.updateOtpDetails(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());

            } else {
                UserRequestedOtp userRequestedOtp = new UserRequestedOtp(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());
                userRequestedOtpDao.save(userRequestedOtp);
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Otp Successfully Generated");

        }
        catch(Exception e)
        {
            System.out.println("Exception:" + e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Otp Request Unsuccessful");
        }
    }


    // Otp Validation
    public ResponseEntity<?> validateOTP(UserValidateOtpRequestModel userValidateOtpRequestModel)
    {

        try {
            UserRequestedOtp userRequestedOtp = userRequestedOtpDao.getById(userValidateOtpRequestModel.getMobileOrEmail());
            String generatedOtp = userRequestedOtp.getOtp();
            int invalidCount = userRequestedOtp.getInvalidCount();

            if(invalidCount>=3)
            {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("You have entered Invalid Otp for more than 3 time! Please Regenerate Otp");
            }

            Date generateTime = userRequestedOtp.getLastGeneratedAt();
            Date currTime = new Date();

            long diff = currTime.getTime() - generateTime.getTime();
            long difference_In_Seconds = TimeUnit.MILLISECONDS.toSeconds(diff);


            if (difference_In_Seconds < 310) {
                if (generatedOtp.equals(userValidateOtpRequestModel.getOtp())) {

                    int res1 = userProfileDetailsDao.updateEmail(userRequestedOtp.getMobileOrEmail(),userValidateOtpRequestModel.getUserId());
                    int res2 = userAccountDetailsDao.updateEmail(userRequestedOtp.getMobileOrEmail(),userValidateOtpRequestModel.getUserId());

                    if(res1>0 && res2>0)
                        return ResponseEntity
                                .status(HttpStatus.OK)
                                .body("Otp Successfully Verified! Email Updated");
                    else
                        return ResponseEntity
                                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Unable to Update Database");

                } else {
                    invalidCount = invalidCount+1;
                    userRequestedOtpDao.updateInvalidCount(userValidateOtpRequestModel.getMobileOrEmail(),invalidCount);

                    if(invalidCount==3)
                    {
                        return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body("Invalid Otp! You have used all you attempts please regenerate the Otp");
                    }
                    else {
                        return ResponseEntity
                                .status(HttpStatus.UNAUTHORIZED)
                                .body("Invalid Otp! You are left with " + String.valueOf(3 - invalidCount) + " attempts");
                    }
                }
            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Otp Expired! Please Generate Again");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

}
