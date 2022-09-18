package com.leggo.fit.dto.userprofile;

import java.util.Date;

public class UserProfileCreateRequestModel {
    private long userId;
    private String gender;
    private Date dob;
    private float weight;
    private float height;

    public UserProfileCreateRequestModel()
    {}

    public UserProfileCreateRequestModel(long userId, String gender, Date dob, float weight, float height) {
        this.userId = userId;
        this.gender = gender;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
