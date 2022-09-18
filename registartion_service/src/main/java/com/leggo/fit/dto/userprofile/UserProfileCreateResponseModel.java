package com.leggo.fit.dto.userprofile;

public class UserProfileCreateResponseModel {
    private long userId;

    public UserProfileCreateResponseModel()
    {

    }

    public UserProfileCreateResponseModel(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
