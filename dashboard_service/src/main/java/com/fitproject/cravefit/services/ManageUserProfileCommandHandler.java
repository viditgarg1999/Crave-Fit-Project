package com.fitproject.cravefit.services;

import com.fitproject.cravefit.dto.userprofile.UserManageProfileDetailsRequestModel;
import com.fitproject.cravefit.dto.userprofile.UserManageProfileDetailsResponseModel;
import com.fitproject.cravefit.entities.UserProfileDetails;
import com.fitproject.cravefit.infrastructure.dao.UserProfileDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;


@Service
public class ManageUserProfileCommandHandler {

    @Autowired
    private UserProfileDetailsDao userProfileDetailsDao;


    public ResponseEntity<?> getProfile(Long userId) {

        try{
            UserProfileDetails userProfileDetails = userProfileDetailsDao.getByUserId(userId);


            int age=0;

            if(userProfileDetails.getDob()!=null) {
                LocalDate userDob = userProfileDetails.getDob();
                LocalDate currDate = LocalDate.now();
                age = Period.between(userDob, currDate).getYears();
            }

            UserManageProfileDetailsResponseModel userManageProfileDetailsResponseModel = new UserManageProfileDetailsResponseModel(userProfileDetails.getName(), userProfileDetails.getGender(),age,
                    userProfileDetails.getWeight(), userProfileDetails.getHeight(), userProfileDetails.getEmail(), userProfileDetails.getMobileNumber());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userManageProfileDetailsResponseModel);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> updateProfile(UserManageProfileDetailsRequestModel userManageProfileDetailsRequestModel) {

        try{

            int res = userProfileDetailsDao.updateUserProfile(userManageProfileDetailsRequestModel.getUserId(), userManageProfileDetailsRequestModel.getName(),
                    userManageProfileDetailsRequestModel.getWeight(), userManageProfileDetailsRequestModel.getHeight(), userManageProfileDetailsRequestModel.getDob(),
                    userManageProfileDetailsRequestModel.getGender(), userManageProfileDetailsRequestModel.getOccupation());


            if(res>0)
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body("Updated Successfully");
            else
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Unable to Update the Database");

        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }

    }


}
