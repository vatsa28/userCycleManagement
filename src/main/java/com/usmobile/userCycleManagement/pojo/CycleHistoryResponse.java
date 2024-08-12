package com.usmobile.userCycleManagement.pojo;

import java.time.Instant;

/**
 * Response POJO to represent the cycle history.
 */
public class CycleHistoryResponse {

    private String cycleId;

    Instant startDate;

    Instant endDate;

    public CycleHistoryResponse() {
    }

    public CycleHistoryResponse(String cycleId, Instant startDate, Instant endDate) {
        this.cycleId = cycleId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCycleId() {
        return cycleId;
    }

    public void setCycleId(String cycleId) {
        this.cycleId = cycleId;
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

    @Override
    public String toString() {
        return "CycleHistoryResponse{" +
                "cycleId='" + cycleId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}