package com.flock.TP_server.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class User {
    private Integer userId;

    private String fullName;
    @Email(message="Invalid Email")
    private String email;
    @NotBlank(message="Password should not be blank")
    @Size(min=8, message="Password should have minimum 8 characters")
    private String password;

    public User() {
    }

    public User(Integer userId, String fullName, String email, String password) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
