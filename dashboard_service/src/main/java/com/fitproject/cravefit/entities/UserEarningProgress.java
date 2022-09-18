package com.fitproject.cravefit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_earning_progress")
public class UserEarningProgress {

    @Id
    @Column(name="user_id")
    private long userId;

    @Column(name="weekly_earning_progress")
    private String weeklyProgress;

    @Column(name="coins_spent")
    private long coinsSpent;
    @Column(name="coins_earned")
    private long coinsEarned;
    @Column(name="coins_balance")
    private long coinsBalance;

    public UserEarningProgress() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(String weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }

    public long getCoinsSpent() {
        return coinsSpent;
    }

    public void setCoinsSpent(long coinsSpent) {
        this.coinsSpent = coinsSpent;
    }

    public long getCoinsEarned() {
        return coinsEarned;
    }

    public void setCoinsEarned(long coinsEarned) {
        this.coinsEarned = coinsEarned;
    }

    public long getCoinsBalance() {
        return coinsBalance;
    }

    public void setCoinsBalance(long coinsBalance) {
        this.coinsBalance = coinsBalance;
    }

    public UserEarningProgress(long userId, String weeklyProgress, long coinsSpent, long coinsEarned, long coinsBalance) {
        this.userId = userId;
        this.weeklyProgress = weeklyProgress;
        this.coinsSpent = coinsSpent;
        this.coinsEarned = coinsEarned;
        this.coinsBalance = coinsBalance;
    }
}
