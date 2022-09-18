package com.fitproject.cravefit.services;

import com.fitproject.cravefit.dto.userprofile.UserUpdatePasswordRequestModel;
import com.fitproject.cravefit.infrastructure.dao.UserAccountDetailsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ManageUserAccountCommandHandler {


    @Autowired
    private UserAccountDetailsDao userAccountDetailsDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public ResponseEntity<?> updatePassword(UserUpdatePasswordRequestModel userManageProfileDetailsRequestModel) {

        try {

            String oldPassword = userAccountDetailsDao.getPassword(userManageProfileDetailsRequestModel.getUserId());

            if (this.bCryptPasswordEncoder.matches(userManageProfileDetailsRequestModel.getOldPassword(),oldPassword)) {

                int res = userAccountDetailsDao.updatePassword(userManageProfileDetailsRequestModel.getUserId(), userManageProfileDetailsRequestModel.getNewPassword());

                if(res>0)
                    return ResponseEntity
                            .status(HttpStatus.OK)
                            .body("Updated Successfully");
                else
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Unable to Update the Database");

            } else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid Old Password! Please try again");
            }
        }
        catch (Exception e)
        {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Exception:" + e);
        }
    }

}
