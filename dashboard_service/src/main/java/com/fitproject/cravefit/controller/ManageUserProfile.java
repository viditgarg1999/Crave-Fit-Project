package com.fitproject.cravefit.controller;

import com.fitproject.cravefit.dto.UserValidateOtpRequestModel;
import com.fitproject.cravefit.dto.userprofile.UserEmailIdRequestModel;
import com.fitproject.cravefit.dto.userprofile.UserManageProfileDetailsRequestModel;
import com.fitproject.cravefit.dto.userprofile.UserUpdatePasswordRequestModel;
import com.fitproject.cravefit.services.ManageUserAccountCommandHandler;
import com.fitproject.cravefit.services.ManageUserProfileCommandHandler;
import com.fitproject.cravefit.services.OtpVerificationCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/manage-profile")
public class ManageUserProfile {

    @Autowired
    private ManageUserProfileCommandHandler manageUserProfileCommandHandler;

    @Autowired
    private OtpVerificationCommandHandler otpVerificationCommandHandler;

    @Autowired
    private ManageUserAccountCommandHandler manageUserAccountCommandHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/get-profile/{userId}")
    public ResponseEntity<?> getProgress(@PathVariable Long userId)
    {
        return manageUserProfileCommandHandler.getProfile(userId);
    }


    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserManageProfileDetailsRequestModel userManageProfileDetailsRequestModel){

        return manageUserProfileCommandHandler.updateProfile(userManageProfileDetailsRequestModel);
    }

    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody UserUpdatePasswordRequestModel userManageProfileDetailsRequestModel){

        userManageProfileDetailsRequestModel.setNewPassword(this.bCryptPasswordEncoder.encode(userManageProfileDetailsRequestModel.getNewPassword()));
        return manageUserAccountCommandHandler.updatePassword(userManageProfileDetailsRequestModel);
    }

    @PutMapping("/update-email")
    public ResponseEntity<?> updateEmail(@RequestBody UserEmailIdRequestModel userEmailIdRequestModel)
    {
        return otpVerificationCommandHandler.generateOTP(userEmailIdRequestModel.getEmail());
    }

    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOTP(@RequestBody UserValidateOtpRequestModel userValidateOtpRequestModel)
    {
        var res = otpVerificationCommandHandler.validateOTP(userValidateOtpRequestModel);
        return res;
    }


}
