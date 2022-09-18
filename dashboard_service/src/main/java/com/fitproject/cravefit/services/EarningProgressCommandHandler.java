package com.fitproject.cravefit.services;

import com.fitproject.cravefit.dto.earningprogress.UserEarningProgressResponseModel;
import com.fitproject.cravefit.entities.UserEarningProgress;
import com.fitproject.cravefit.infrastructure.dao.UserEarningProgressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class EarningProgressCommandHandler {

    @Autowired
    private UserEarningProgressDao userEarningProgressDao;


    public ResponseEntity<?> getProgress(Long userId) {

        try {
            UserEarningProgress userEarningProgress = userEarningProgressDao.getById(userId);

            List<String> weeklyEarningProgress = Arrays.asList(userEarningProgress.getWeeklyProgress().split("\\s*,\\s*"));

            List<Integer> weeklyProgress = new ArrayList<Integer>();

            for(String weekDay : weeklyEarningProgress) {
                try {
                    weeklyProgress.add(Integer.parseInt(weekDay));
                } catch(NumberFormatException e) {
                    System.out.println("Exception:"+ e);
                }
            }

            UserEarningProgressResponseModel userEarningProgressResponseModel = new UserEarningProgressResponseModel(weeklyProgress,
                    userEarningProgress.getCoinsSpent(), userEarningProgress.getCoinsEarned(), userEarningProgress.getCoinsBalance());

            return ResponseEntity.ok(userEarningProgressResponseModel);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }
}
