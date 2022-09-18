package com.fitproject.cravefit.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name="user_activity_progress")
public class UserActivityProgress {

    @Id
    @Column(name="user_id")
    private long userId;
    @Column(name="streak")
    private int streak;

    @Column(name="weekly_activity_progress") // Time in Minutes   0-> sunday, 1-> monday 2-> tuesday 3->wednesday 4-> thursday 5-> friday 6-> saturday
    private String weeklyProgress;

    @Column(name="frequent_activities")
    private String frequentActivities;

    @Column(name="last_activity_date")
    private LocalDate lastActivityDate;  // Date dd:mm:yyyy

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public String getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(String weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }

    public String getFrequentActivities() {
        return frequentActivities;
    }

    public void setFrequentActivities(String frequentActivities) {
        this.frequentActivities = frequentActivities;
    }

    public LocalDate getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(LocalDate lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public UserActivityProgress() {
    }

    public UserActivityProgress(long userId, int streak, String weeklyProgress, String frequentActivities, LocalDate lastActivityDate) {
        this.userId = userId;
        this.streak = streak;
        this.weeklyProgress = weeklyProgress;
        this.frequentActivities = frequentActivities;
        this.lastActivityDate = lastActivityDate;
    }
}
