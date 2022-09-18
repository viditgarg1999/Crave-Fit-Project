package com.fitproject.cravefit.services;

import com.fitproject.cravefit.dto.activityprogress.UserActivityProgressResponseModel;
import com.fitproject.cravefit.dto.activityprogress.UserUpdateActivityProgressRequestModel;
import com.fitproject.cravefit.entities.UserActivityProgress;
import com.fitproject.cravefit.entities.UserActivityRecords;
import com.fitproject.cravefit.infrastructure.dao.UserActivityProgressDao;
import com.fitproject.cravefit.infrastructure.dao.UserActivityRecordsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ActivityProgressCommandHandler {

    @Autowired
    private UserActivityProgressDao userActivityProgressDao;

    @Autowired
    private UserActivityRecordsDao userActivityRecordsDao;

    public ResponseEntity<?> getProgress(Long userId) {

        try {
            UserActivityProgress userActivityProgress = userActivityProgressDao.getById(userId);
            List<String> frequentActivitiesList = Arrays.asList(userActivityProgress.getFrequentActivities().split("\\s*,\\s*"));
            List<String> weeklyActivityProgress = Arrays.asList(userActivityProgress.getWeeklyProgress().split("\\s*,\\s*"));

            List<Integer> weeklyProgress = new ArrayList<Integer>();

            for(String weekDay : weeklyActivityProgress) {
                try {
                    weeklyProgress.add(Integer.parseInt(weekDay));
                } catch(NumberFormatException e) {
                    System.out.println("Exception:"+ e);
                }
            }

            UserActivityProgressResponseModel userActivityProgressResponseModel = new UserActivityProgressResponseModel(userActivityProgress.getStreak(),weeklyProgress, frequentActivitiesList);

            return ResponseEntity.ok(userActivityProgressResponseModel);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }


    public ResponseEntity<?> updateProgress(UserUpdateActivityProgressRequestModel userUpdateActivityProgressRequestModel) {

        try {
            UserActivityProgress userActivityProgress = userActivityProgressDao.getById(userUpdateActivityProgressRequestModel.getUserId());

            LocalDate startDate = userActivityProgress.getLastActivityDate();
            LocalDate endDate = LocalDate.now();

            Long difference_In_Days = ChronoUnit.DAYS.between(startDate, endDate);


            Calendar calendar = Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            List<String> weeklyActivityProgress = Arrays.asList(userActivityProgress.getWeeklyProgress().split("\\s*,\\s*"));

            List<Integer> weeklyProgressList = new ArrayList<Integer>();
            for(String weekDay : weeklyActivityProgress) {
                try {
                    weeklyProgressList.add(Integer.parseInt(weekDay));
                } catch(NumberFormatException e) {
                    System.out.println("Exception:"+ e);
                }
            }


            if(difference_In_Days<1)
                weeklyProgressList.set(dayOfWeek-1,weeklyProgressList.get(dayOfWeek-1)+userUpdateActivityProgressRequestModel.getActivityTime());
            else
                weeklyProgressList.set(dayOfWeek-1,userUpdateActivityProgressRequestModel.getActivityTime());


            for(int i=dayOfWeek;i<7;i++)
                weeklyProgressList.set(i,0);


            String weeklyProgress= String.valueOf(weeklyProgressList.get(0));

            for(int i=1;i< weeklyProgressList.size();i++)
            {
                weeklyProgress+=","+ weeklyProgressList.get(i);
            }

            userActivityProgress.setWeeklyProgress(weeklyProgress);

            List<String> frequentActivitiesList = new LinkedList<>(Arrays.asList(userActivityProgress.getFrequentActivities().split("\\s*,\\s*")));

            // Frequent Activities logic for Dashboard Screen
            int index = frequentActivitiesList.indexOf(userUpdateActivityProgressRequestModel.getActivityType());

            if(index==-1)
                frequentActivitiesList.remove(5);
            else
                frequentActivitiesList.remove(index);

            frequentActivitiesList.add(0,userUpdateActivityProgressRequestModel.getActivityType());

            String frequentActivities=frequentActivitiesList.get(0);

            for(int i=1;i< frequentActivitiesList.size();i++)
            {
                frequentActivities+=","+frequentActivitiesList.get(i);
            }

            userActivityProgress.setFrequentActivities(frequentActivities);

            int res = userActivityProgressDao.updateProfileById(userActivityProgress.getUserId(),userActivityProgress.getWeeklyProgress(),
                    userActivityProgress.getFrequentActivities(), userActivityProgress.getLastActivityDate());

            UserActivityRecords userActivityRecords = new UserActivityRecords(userUpdateActivityProgressRequestModel.getUserId(), userUpdateActivityProgressRequestModel.getActivityType(), userUpdateActivityProgressRequestModel.getStartTime(),
                    userUpdateActivityProgressRequestModel.getEndTime(), userUpdateActivityProgressRequestModel.getCenterLocation());

            userActivityRecordsDao.save(userActivityRecords);

            if(res!=0)
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Progress Updated Successfully");
            else
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Unable to Update the progress");

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }
}
