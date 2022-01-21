package com.flock.TP_server.models;

import java.sql.Timestamp;

public class AuthToken {

    private Integer userId;
    private String token;

    public AuthToken() {
    }

    public AuthToken(Integer userId) {
        this.userId = userId;
        this.token = (new Timestamp(System.currentTimeMillis()).toString()) + userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String authToken) {
        this.token = authToken;
    }
}
