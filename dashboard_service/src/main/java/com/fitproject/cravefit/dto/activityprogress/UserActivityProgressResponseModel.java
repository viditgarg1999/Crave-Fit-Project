package com.fitproject.cravefit.dto.activityprogress;

import java.util.List;

public class UserActivityProgressResponseModel {

    private int streak;

    private List<Integer> weeklyProgress;
    private List<String> frequentActivities;

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public List<Integer> getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(List<Integer> weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }

    public List<String> getFrequentActivities() {
        return frequentActivities;
    }

    public void setFrequentActivities(List<String> frequentActivities) {
        this.frequentActivities = frequentActivities;
    }

    public UserActivityProgressResponseModel() {
    }

    public UserActivityProgressResponseModel(int streak, List<Integer> weeklyProgress, List<String> frequentActivities) {
        this.streak = streak;
        this.weeklyProgress = weeklyProgress;
        this.frequentActivities = frequentActivities;
    }
}
