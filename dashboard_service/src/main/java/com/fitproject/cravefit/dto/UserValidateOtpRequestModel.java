package com.fitproject.cravefit.dto;

public class UserValidateOtpRequestModel {

    private long userId;
    private String mobileOrEmail;
    private String otp;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getMobileOrEmail() {
        return mobileOrEmail;
    }

    public void setMobileOrEmail(String mobileOrEmail) {
        this.mobileOrEmail = mobileOrEmail;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public UserValidateOtpRequestModel() {
    }

    public UserValidateOtpRequestModel(long userId, String mobileNumber, String otp) {
        this.userId = userId;
        this.mobileOrEmail = mobileNumber;
        this.otp = otp;
    }
}
