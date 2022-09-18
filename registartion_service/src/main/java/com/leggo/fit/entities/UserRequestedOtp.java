package com.leggo.fit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="user_requested_otp")
public class UserRequestedOtp {

    @Id
    @Column(name="mobile_or_email")
    private String mobileOrEmail;
    @Column(name="otp")
    private String otp;
    @Column(name="last_generated_at")
    private Date lastGeneratedAt;
    @Column(name="invalid_count")
    private int invalidCount;

    public UserRequestedOtp() {
    }

    public String getMobileOrEmail() {
        return mobileOrEmail;
    }

    public void setMobileOrEmail(String mobileNumber) {
        this.mobileOrEmail = mobileNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Date getLastGeneratedAt() {
        return lastGeneratedAt;
    }

    public void setLastGeneratedAt(Date lastGeneratedAt) {
        this.lastGeneratedAt = lastGeneratedAt;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(int invalidCount) {
        this.invalidCount = invalidCount;
    }

    public UserRequestedOtp(String mobileOrEmail, String otp, Date lastGeneratedAt) {
        this.mobileOrEmail = mobileOrEmail;
        this.otp = otp;
        this.lastGeneratedAt = lastGeneratedAt;
        this.invalidCount = 0;
    }
}
