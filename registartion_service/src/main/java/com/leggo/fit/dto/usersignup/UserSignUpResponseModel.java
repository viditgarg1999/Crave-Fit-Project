package com.leggo.fit.dto.usersignup;

public class UserSignUpResponseModel {
    private long userId;

    public UserSignUpResponseModel(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
