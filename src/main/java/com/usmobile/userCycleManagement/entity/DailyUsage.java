package com.usmobile.userCycleManagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "dailyUsage")
public class DailyUsage {
    @Id
    private String id;
    private String mdn;
    private String userId;
    private Instant usageDate;
    private double usedInMb;

    public DailyUsage() {
    }

    public DailyUsage(String id, String mdn, String userId, Instant usageDate, double usedInMb) {
        this.id = id;
        this.mdn = mdn;
        this.userId = userId;
        this.usageDate = usageDate;
        this.usedInMb = usedInMb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Instant getUsageDate() {
        return usageDate;
    }

    public void setUsageDate(Instant usageDate) {
        this.usageDate = usageDate;
    }

    public double getUsedInMb() {
        return usedInMb;
    }

    public void setUsedInMb(double usedInMb) {
        this.usedInMb = usedInMb;
    }

    @Override
    public String toString() {
        return "DailyUsage{" +
                "id='" + id + '\'' +
                ", mdn='" + mdn + '\'' +
                ", userId='" + userId + '\'' +
                ", usageDate=" + usageDate +
                ", usedInMb=" + usedInMb +
                '}';
    }
}
