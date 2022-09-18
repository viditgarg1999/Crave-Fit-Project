package com.leggo.fit.dto.login;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserLoginRequestModel {

    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "\\d{10}")
    private String mobileNumber;

    @NotBlank(message = "Password is mandatory")
    private String password;
    public UserLoginRequestModel(){}
    public UserLoginRequestModel(String mobileNumber, String password) {
        this.mobileNumber = mobileNumber;
        this.password = password;
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
        return "LoginRequestModel{" +
                "mobileNumber='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
