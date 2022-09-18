package com.leggo.fit.dto;

public class ForgotPasswordRequestModel {

    private String mobileOrEmail;
    private String otp;
    private String password;

    public ForgotPasswordRequestModel() {
    }

    public String getmobileOrEmail() {
        return mobileOrEmail;
    }

    public void setmobileOrEmail(String userId) {
        this.mobileOrEmail = userId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ForgotPasswordRequestModel(String mobileOrEmail, String otp, String password) {
        this.mobileOrEmail = mobileOrEmail;
        this.otp = otp;
        this.password = password;
    }
}
