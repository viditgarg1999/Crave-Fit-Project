package com.leggo.fit.services;

import com.leggo.fit.dto.ForgotPasswordRequestModel;
import com.leggo.fit.infrastructure.dao.UserAccountDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserAccountUpdateCommandHandler {

    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    public ResponseEntity<?> handle(ForgotPasswordRequestModel forgotPasswordRequestModel)
    {
        try {

            var res = userAccountDetailsDao.updatePasswordViaMobileNumber(forgotPasswordRequestModel.getmobileOrEmail(),forgotPasswordRequestModel.getPassword());

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Password Successfully Updated");
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

    public ResponseEntity<?> getMobilefromEmail(String email)
    {
        try {

            String res = userAccountDetailsDao.getMobilefromEmail(email);

            if(res==null)
                return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Email Id doesn't Exists");

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(res);
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }



}
