package com.fitproject.cravefit.dto.userprofile;

import java.time.LocalDate;

public class UserManageProfileDetailsRequestModel {

    private long userId;
    private String name;
    private String gender;
    private LocalDate dob;
    private float weight;
    private float height;
    private String occupation;

    public UserManageProfileDetailsRequestModel(long userId, String name, String gender, LocalDate dob, float weight, float height, String occupation) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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

    public UserManageProfileDetailsRequestModel() {
    }

}
