package com.fitproject.cravefit.dto.activityprogress;

import java.util.Date;

public class UserUpdateActivityProgressRequestModel {

    private long userId;
    private int activityTime;
    private String activityType;
    private Date startTime;
    private Date endTime;
    private String centerLocation;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(int activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public UserUpdateActivityProgressRequestModel() {
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

    public UserUpdateActivityProgressRequestModel(long userId, int activityTime, String activityType, Date startTime, Date endTime, String centerLocation) {
        this.userId = userId;
        this.activityTime = activityTime;
        this.activityType = activityType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.centerLocation = centerLocation;
    }
}
