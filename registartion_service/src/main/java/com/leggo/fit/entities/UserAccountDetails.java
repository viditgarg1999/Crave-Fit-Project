package com.leggo.fit.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_account")
@SQLDelete(sql = "UPDATE user_account SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class UserAccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="name")
    private  String name;
    @Column(name="mobile_number")
    private String mobileNumber;
    @Column(name="password")
    private String password;
    @Column(name="created_at")
    private Date createdAt;
    @Column(name="is_deleted")
    private boolean isDeleted;
    @Column(name="email_id")
    private String emailId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public UserAccountDetails() {
    }

    public UserAccountDetails(String name, String mobileNumber, String password, Date createdAt) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.createdAt = createdAt;
        this.isDeleted = false;
    }
}
