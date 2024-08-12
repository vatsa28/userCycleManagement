package com.usmobile.userCycleManagement.pojo;

public class CycleRequest {

    private String userId;

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
