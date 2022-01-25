package com.flock.TP_server.models;

public class LatestTransactionDetails {
    private String latestId;

    public LatestTransactionDetails(String latestId) {
        this.latestId = latestId;
    }

    public String getLatestId() {
        return latestId;
    }

    public void setLatestId(String latestId) {
        this.latestId = latestId;
    }
}
