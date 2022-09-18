package com.fitproject.cravefit.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="user_activity_records")
public class UserActivityRecords {

    @Id
    @Column(name="user_id")
    private long userId;
    @Column(name="activity_type")
    private String activityType;
    @Column(name="start_time")
    private Date startTime;
    @Column(name="end_time")
    private Date endTime;
    @Column(name="center_location")
    private String centerLocation;

    public UserActivityRecords() {
    }

    public UserActivityRecords(long userId, String activityType, Date startTime, Date endTime, String centerLocation) {
        this.userId = userId;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.centerLocation = centerLocation;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCenterLocation() {
        return centerLocation;
    }

    public void setCenterLocation(String centerLocation) {
        this.centerLocation = centerLocation;
    }
}
