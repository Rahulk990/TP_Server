package com.flock.TP_server.models;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;


public class AuthToken {
    @Positive(message="Invalid UserId")
    private Integer userId;
    @NotBlank(message="Invalid Token")
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
