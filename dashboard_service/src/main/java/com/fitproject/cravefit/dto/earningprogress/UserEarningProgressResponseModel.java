package com.fitproject.cravefit.dto.earningprogress;

import java.util.List;

public class UserEarningProgressResponseModel {

    private List<Integer> weeklyProgress;
    private long coinsSpent;
    private long coinsEarned;
    private long coinsBalance;

    public UserEarningProgressResponseModel() {
    }

    public List<Integer> getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(List<Integer> weeklyProgress) {
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

    public UserEarningProgressResponseModel(List<Integer> weeklyProgress, long coinsSpent, long coinsEarned, long coinsBalance) {
        this.weeklyProgress = weeklyProgress;
        this.coinsSpent = coinsSpent;
        this.coinsEarned = coinsEarned;
        this.coinsBalance = coinsBalance;
    }
}
