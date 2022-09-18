package com.fitproject.cravefit.controller;

import com.fitproject.cravefit.dto.activityprogress.UserUpdateActivityProgressRequestModel;
import com.fitproject.cravefit.services.ActivityProgressCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activity-progress")
public class ManageUserActivityProgress {


    @Autowired
    private ActivityProgressCommandHandler activityProgressCommandHandler;

    @GetMapping("/get-progress/{userId}")
    public ResponseEntity<?> getProgress(@PathVariable Long userId)
    {
        return activityProgressCommandHandler.getProgress(userId);
    }

    @PostMapping("/add-progress")
    public ResponseEntity<?> updateProgress(@RequestBody UserUpdateActivityProgressRequestModel userUpdateActivityProgressRequestModel)
    {
        return activityProgressCommandHandler.updateProgress(userUpdateActivityProgressRequestModel);
    }


}
