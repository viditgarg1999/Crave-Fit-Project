package com.leggo.fit.controller;

import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.UserValidateOtpRequestModel;
import com.leggo.fit.dto.login.RefreshTokenRequestModel;
import com.leggo.fit.dto.login.UserLoginRequestModel;
import com.leggo.fit.services.UserLoginCommandHandler;
import com.leggo.fit.services.OtpVerificationCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserLoginCommandHandler userLoginCommandHandler;

    @Autowired
    private OtpVerificationCommandHandler otpVerification;



    @PostMapping("/via-password")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestModel userLoginRequestModel)
    {
        var res= userLoginCommandHandler.handle(userLoginRequestModel);
        return res;
    }



    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@Valid @RequestBody MobileNumberRequestModel requestedMobileNumber)
    {
        var res= otpVerification.generateOTP(requestedMobileNumber.getMobileNumber());
        return res;
    }

    @PostMapping("/via-otp")
    public ResponseEntity<?> loginViaOtp(@Valid @RequestBody MobileNumberRequestModel requestedMobileNumber)
    {
        var res= userLoginCommandHandler.handleLoginViaOtp(requestedMobileNumber);
        return res;
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody UserValidateOtpRequestModel userValidateOtpRequestModel)
    {
        var res = otpVerification.validateOTP(userValidateOtpRequestModel,"login");
        return res;
    }

    @GetMapping("/validate-refresh-token")
    public ResponseEntity<?> validateRefreshToken(@RequestBody RefreshTokenRequestModel refreshTokenRequestModel)
    {
        var res=userLoginCommandHandler.validateRefreshToken(refreshTokenRequestModel);
        return res;
    }

}