package com.leggo.fit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name="temporary_user_account")
public class TemporaryUserAccountDetails {

    @Id
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="createdAt")
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TemporaryUserAccountDetails() {
    }

    public TemporaryUserAccountDetails(String mobileNumber, String name, String password, Date createdAt) {
        this.mobileNumber = mobileNumber;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
    }
}
