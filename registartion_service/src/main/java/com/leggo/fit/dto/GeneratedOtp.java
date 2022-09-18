package com.leggo.fit.dto;

import java.util.Date;

public class GeneratedOtp {

    private String otp;
    private Date createdAt;

    public GeneratedOtp() {
    }

    public GeneratedOtp(String otp, Date createdAt) {
        this.otp = otp;
        this.createdAt = createdAt;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
