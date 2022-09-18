package com.fitproject.cravefit.controller;

import com.fitproject.cravefit.services.EarningProgressCommandHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/earning-progress")
public class ManageUserEarningProgress {

    @Autowired
    private EarningProgressCommandHandler earningProgressCommandHandler;

    @GetMapping("/get-progress/{userId}")
    public ResponseEntity<?> getProgress(@PathVariable Long userId)
    {
        return earningProgressCommandHandler.getProgress(userId);
    }

}
