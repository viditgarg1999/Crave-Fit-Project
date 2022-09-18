package com.fitproject.cravefit.dto.userprofile;

public class UserEmailIdRequestModel {

    private Long userId;
    private String email;

    public UserEmailIdRequestModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserEmailIdRequestModel(Long userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
