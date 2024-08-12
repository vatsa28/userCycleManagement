package com.usmobile.userCycleManagement.pojo;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Response POJO to represent the daily usage of a user.
 */
public class DailyUsageResponse {

    Instant date;

    double dailyUsage;

    public DailyUsageResponse() {
    }

    public DailyUsageResponse(Instant date, double dailyUsage) {
        this.date = date;
        this.dailyUsage = dailyUsage;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public double getDailyUsage() {
        return dailyUsage;
    }

    public void setDailyUsage(double dailyUsage) {
        this.dailyUsage = dailyUsage;
    }

    @Override
    public String toString() {
        return "DailyUsageResponse{" +
                "date='" + date + '\'' +
                ", dailyUsage=" + dailyUsage +
                '}';
    }
}