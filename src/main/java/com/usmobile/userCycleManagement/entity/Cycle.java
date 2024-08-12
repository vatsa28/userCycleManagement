package com.usmobile.userCycleManagement.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Entity class for Cycle
 */
@Document(collection = "cycle")
public class Cycle {

    @Id
    private String id;
    private String mdn;
    private Instant startDate;
    private Instant endDate;
    private String userId;

    public Cycle() {
    }

    public Cycle(String id, String mdn, Instant startDate, Instant endDate, String userId) {
        this.id = id;
        this.mdn = mdn;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
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

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Cycle{" +
                "id='" + id + '\'' +
                ", mdn='" + mdn + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
