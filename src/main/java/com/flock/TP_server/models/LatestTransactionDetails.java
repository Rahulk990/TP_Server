package com.flock.TP_server.models;

public class LatestTransactionDetails {
    private Long latestId;

    public LatestTransactionDetails(Long latestId) {
        this.latestId = latestId;
    }

    public Long getLatestId() {
        return latestId;
    }

    public void setLatestId(Long latestId) {
        this.latestId = latestId;
    }
}
