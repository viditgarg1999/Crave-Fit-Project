package com.fitproject.cravefit.dto.userprofile;

public class UserUpdatePasswordRequestModel {

    private long userId;
    private String oldPassword;
    private String newPassword;

    public UserUpdatePasswordRequestModel() {
    }

    public UserUpdatePasswordRequestModel(long userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
