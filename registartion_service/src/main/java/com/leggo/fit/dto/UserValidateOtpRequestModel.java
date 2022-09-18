package com.leggo.fit.dto;

public class UserValidateOtpRequestModel {

    private String mobileOrEmail;
    private String otp;

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

    public UserValidateOtpRequestModel(String mobileNumber, String otp) {
        this.mobileOrEmail = mobileNumber;
        this.otp = otp;
    }
}
