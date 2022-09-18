package com.leggo.fit.services;

import com.leggo.fit.dto.userprofile.UserProfileUpdateRequestModel;
import com.leggo.fit.infrastructure.dao.UserProfileDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class UserProfileUpdateCommandHandler {

    @Autowired
    private UserProfileDetailsDao userProfileDetailsDao;

    public ResponseEntity<String> handle(UserProfileUpdateRequestModel userProfileUpdateRequestModel) throws ParseException {

        try {

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(userProfileUpdateRequestModel.getDob(), pattern);



            //Date dob = new SimpleDateFormat("dd-MM-yyyy").parse(userProfileUpdateRequestModel.getDob());
            int res = userProfileDetailsDao.updateDetails(userProfileUpdateRequestModel.getUserAccountId(), dob, userProfileUpdateRequestModel.getGender(), userProfileUpdateRequestModel.getHeight(), userProfileUpdateRequestModel.getWeight());

            if (res == 0)
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("User Id doesn't exists");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Build Successfully");
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }
}
