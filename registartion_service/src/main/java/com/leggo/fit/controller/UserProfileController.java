package com.leggo.fit.controller;


import com.leggo.fit.dto.userprofile.UserProfileUpdateRequestModel;
import com.leggo.fit.services.UserProfileUpdateCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/updateprofile")
public class UserProfileController {


    @Autowired
    private UserProfileUpdateCommandHandler userProfileUpdateCommandHandler;

    @PutMapping("/build-profile")
    public ResponseEntity<?> registerUser(@RequestBody UserProfileUpdateRequestModel userProfileUpdateRequestModel) throws ParseException {
        var res = userProfileUpdateCommandHandler.handle(userProfileUpdateRequestModel);
        return res;
    }

}
