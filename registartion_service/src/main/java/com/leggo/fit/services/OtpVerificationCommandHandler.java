package com.leggo.fit.services;

import com.leggo.fit.dto.ForgotPasswordRequestModel;
import com.leggo.fit.dto.GeneratedOtp;
import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.UserValidateOtpRequestModel;
import com.leggo.fit.entities.UserRequestedOtp;
import com.leggo.fit.infrastructure.dao.TemporaryUserAccountDetailsDao;
import com.leggo.fit.infrastructure.dao.UserAccountDetailsDao;
import com.leggo.fit.infrastructure.dao.UserRequestedOtpDao;
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
    private TemporaryUserAccountDetailsDao temporaryUserAccountDetailsDao;

    @Autowired
    private UserRequestedOtpDao userRequestedOtpDao;
    @Autowired
    private UserLoginCommandHandler loginService;

    @Autowired
    private UserSignUpCommandHandler signupService;

    @Autowired
    private UserAccountUpdateCommandHandler userAccountUpdateCommandHandler;

    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    public ResponseEntity<?> generateOTP(String mobileOrEmail)
    {
        try {
            String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));

            Date date = new Date();
            Timestamp currTime = new Timestamp(date.getTime());

            GeneratedOtp generatedOtp = new GeneratedOtp(otp, currTime);

            // To send sms via sms gateway
            // To generate otp

            if(mobileOrEmail.contains("@"))
            {
                System.out.println("Sent Otp on Email");
            }
            else
            {
                System.out.println("Send Otp on Mobile");
            }

            if (userRequestedOtpDao.existsById(mobileOrEmail)) {
                // Update the user details
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

    public ResponseEntity<?> generateForgotOTP(String mobileOrEmail)
    {
        try {
            String otp = new DecimalFormat("0000").format(new Random().nextInt(9999));

            Date date = new Date();
            Timestamp currTime = new Timestamp(date.getTime());

            GeneratedOtp generatedOtp = new GeneratedOtp(otp, currTime);

            // To send sms via sms gateway
            // To generate otp

            if(mobileOrEmail.contains("@"))
            {

                if (userAccountDetailsDao.existsByEmailId(mobileOrEmail)) {
                    if (userRequestedOtpDao.existsById(mobileOrEmail)) {
                        // Update the user details
                        int updatedRows = userRequestedOtpDao.updateOtpDetails(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());

                    } else {
                        UserRequestedOtp userRequestedOtp = new UserRequestedOtp(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());
                        userRequestedOtpDao.save(userRequestedOtp);
                    }
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Otp Successfully Generated");
                }
                else
                {
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Email doesn't exist");
                }
            }
            else
            {
                if (userAccountDetailsDao.existsByMobileNumber(mobileOrEmail)) {
                    if (userRequestedOtpDao.existsById(mobileOrEmail)) {
                        // Update the user details
                        int updatedRows = userRequestedOtpDao.updateOtpDetails(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());

                    } else {
                        UserRequestedOtp userRequestedOtp = new UserRequestedOtp(mobileOrEmail, generatedOtp.getOtp(), generatedOtp.getCreatedAt());
                        userRequestedOtpDao.save(userRequestedOtp);
                    }
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Otp Successfully Generated");
                }
                else
                {
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Mobile Number doesn't exist");
                }
            }
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
    public ResponseEntity<?> validateOTP(UserValidateOtpRequestModel userValidateOtpRequestModel, String type)
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

                    MobileNumberRequestModel mobileNumberRequestModel = new MobileNumberRequestModel(userValidateOtpRequestModel.getMobileOrEmail());

                    if(type.equals("signup")) {        // For Signup Valid Otp Request
                        var res = signupService.handle(mobileNumberRequestModel);
                        return res;
                    }
                    else if(type.equals("login"))
                    {                           // For login Otp Request
                        var res=loginService.validateOtpLogin(mobileNumberRequestModel.getMobileNumber());
                        return res;
                    }

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
            }
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Otp Expired! Please Generate Again");

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }


    // Otp Validation for Forgot password
    public ResponseEntity<?> validateForgotOTPRequest(ForgotPasswordRequestModel forgotPasswordRequestModel)
    {

        try {
            UserRequestedOtp userRequestedOtp = userRequestedOtpDao.getById(forgotPasswordRequestModel.getmobileOrEmail());
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
                if (generatedOtp.equals(forgotPasswordRequestModel.getOtp())) {

                    // To check for Email Id
                    String userId = forgotPasswordRequestModel.getmobileOrEmail();
                    if(userId.contains("@"))
                    {
                        var res = userAccountUpdateCommandHandler.getMobilefromEmail(userId);
                        HttpStatus status = res.getStatusCode();
                        if(status == HttpStatus.OK)
                            forgotPasswordRequestModel.setmobileOrEmail((String) res.getBody());
                        else
                            return res;
                    }

                    var res = userAccountUpdateCommandHandler.handle(forgotPasswordRequestModel);
                    return res;

                } else {
                    invalidCount = invalidCount+1;
                    userRequestedOtpDao.updateInvalidCount(forgotPasswordRequestModel.getmobileOrEmail(),invalidCount);

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


    public ResponseEntity<?> handleResendOtp(MobileNumberRequestModel requestedMobileNumber) {

        return generateOTP(requestedMobileNumber.getMobileNumber());
    }
}
