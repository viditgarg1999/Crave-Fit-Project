package com.leggo.fit.controller;

import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.usersignup.UserSignUpRequestModel;
import com.leggo.fit.dto.UserValidateOtpRequestModel;
import com.leggo.fit.services.OtpVerificationCommandHandler;
import com.leggo.fit.services.UserSignUpCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private UserSignUpCommandHandler signupService;

    @Autowired
    private OtpVerificationCommandHandler otpVerification;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@Valid @RequestBody MobileNumberRequestModel requestedMobileNumber)
    {
        var res= otpVerification.generateOTP(requestedMobileNumber.getMobileNumber());
        return res;
    }


    @PostMapping("/register/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody UserValidateOtpRequestModel userValidateOtpRequestModel)
    {
        var res = otpVerification.validateOTP(userValidateOtpRequestModel,"signup");
        return res;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserSignUpRequestModel userSignUpRequestModel)
    {
        userSignUpRequestModel.setPassword(this.bCryptPasswordEncoder.encode(userSignUpRequestModel.getPassword()));
        var res=signupService.handleUnapprovedRequest(userSignUpRequestModel);
        return res;
    }

}
