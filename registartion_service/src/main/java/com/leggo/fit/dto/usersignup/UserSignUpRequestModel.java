package com.leggo.fit.dto.usersignup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserSignUpRequestModel {
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "\\d{10}")
    private String mobileNumber;
    @NotBlank(message = "Password is mandatory")
    private String password;

    public UserSignUpRequestModel(String name, String mobileNumber, String password) {
        this.name=name;
        this.mobileNumber = mobileNumber;
        this.password = password;
    }
    public UserSignUpRequestModel()
    {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignUpRequestModel{" +
                "name='" + name + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
