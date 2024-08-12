package com.usmobile.userCycleManagement.pojo;

import jakarta.validation.constraints.NotBlank;

/**
 * Request POJO for all the cycle Requests
 */
public class CycleRequest {

    @NotBlank(message = "userId is mandatory")
    private String userId;

    @NotBlank(message = "mdn is mandatory")
    private String mdn;

    public CycleRequest() {
    }

    public CycleRequest(String userId, String mdn) {
        this.userId = userId;
        this.mdn = mdn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMdn() {
        return mdn;
    }

    public void setMdn(String mdn) {
        this.mdn = mdn;
    }

    @Override
    public String toString() {
        return "CycleRequest{" +
                "userId='" + userId + '\'' +
                ", mdn='" + mdn + '\'' +
                '}';
    }
}
