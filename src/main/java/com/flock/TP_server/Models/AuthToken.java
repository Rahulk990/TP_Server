package com.flock.TP_server.Models;

import java.sql.Timestamp;

public class AuthToken {

    private int userId;
    private String token;

    public AuthToken() {}

    public AuthToken(int userId) {
        this.userId = userId;
        this.token = (new Timestamp(System.currentTimeMillis()).toString()) + String.valueOf(userId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String authToken) {
        this.token = authToken;
    }
}
