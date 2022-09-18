package com.leggo.fit.dto.userprofile;

public class UserProfileUpdateRequestModel {

    private long userAccountId;
    private String gender;
    private String dob;
    private float weight;
    private float height;

    public UserProfileUpdateRequestModel(long userAccountId, String gender, String dob, float weight, float height) {
        this.userAccountId = userAccountId;
        this.gender = gender;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public UserProfileUpdateRequestModel() {
    }

    public long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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
