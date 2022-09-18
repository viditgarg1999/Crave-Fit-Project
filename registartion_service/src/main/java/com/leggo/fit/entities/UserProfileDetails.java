package com.leggo.fit.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfileDetails {
    @Id
    @Column(name="user_id")
    private long userId;
    @Column(name="name")
    private String name;
    @Column(name="email")
    private String email;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="gender")
    private String gender;
    @Column(name="dob")
    private LocalDate dob;
    @Column(name="weight")
    private float weight;
    @Column(name="height")
    private float height;
    @Column(name="occupation")
    private String occupation;

    public UserProfileDetails(long userId, String name, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public UserProfileDetails() {
    }

    public UserProfileDetails(long userId, String name, String email, String mobileNumber, String gender, LocalDate dob, float weight, float height) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.dob = dob;
        this.weight = weight;
        this.height = height;
    }

    public UserProfileDetails(long userId, String name, String email, String mobileNumber, String gender, LocalDate dob, float weight, float height, String occupation) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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
}
