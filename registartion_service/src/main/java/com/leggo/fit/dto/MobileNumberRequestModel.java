package com.leggo.fit.dto;

public class MobileNumberRequestModel {

    private String mobileNumber;

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public MobileNumberRequestModel() {
    }

    public MobileNumberRequestModel(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
