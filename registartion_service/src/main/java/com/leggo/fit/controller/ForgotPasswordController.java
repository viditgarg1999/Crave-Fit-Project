package com.leggo.fit.controller;

import com.leggo.fit.dto.EmailIdRequestModel;
import com.leggo.fit.dto.MobileNumberRequestModel;
import com.leggo.fit.dto.ForgotPasswordRequestModel;
import com.leggo.fit.services.OtpVerificationCommandHandler;
import com.leggo.fit.services.UserAccountUpdateCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/forgot")
public class ForgotPasswordController {

    @Autowired
    private OtpVerificationCommandHandler otpVerification;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserAccountUpdateCommandHandler userAccountUpdateCommandHandler;

    @PostMapping("/via-phone")
    public ResponseEntity<?> sendOtpViaPhone(@Valid @RequestBody MobileNumberRequestModel requestedMobileNumber)
    {
        var res= otpVerification.generateForgotOTP(requestedMobileNumber.getMobileNumber());
        return res;
    }

    @PostMapping("/via-email")
    public ResponseEntity<?> sendOtpViaEmail(@Valid @RequestBody EmailIdRequestModel emailIdRequestModel)
    {
        var res= otpVerification.generateForgotOTP(emailIdRequestModel.getEmail());
        return res;
    }


    @PostMapping("/new-password")
    public ResponseEntity<?> newPassword(@Valid @RequestBody ForgotPasswordRequestModel forgotPasswordRequestModel)
    {
        forgotPasswordRequestModel.setPassword(this.bCryptPasswordEncoder.encode(forgotPasswordRequestModel.getPassword()));
        var res = otpVerification.validateForgotOTPRequest(forgotPasswordRequestModel);
        return res;
    }

}
