package com.flock.TP_server.Models;

import java.sql.Timestamp;

public class Contact {
    private int userId;
    private String contactId;
    private String fullName;
    private String email="";
    private String address="";
    private String phoneNumber="";
    private int score = 0;

    public Contact() {}

    public Contact(int userId, String fullName, String email, String address, String phoneNumber) {
        this.userId = userId;
        this.contactId = (new Timestamp(System.currentTimeMillis()).toString()) + String.valueOf(userId);
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.score = 0;
    }

    public int getUserId() {
        return userId;
    }

    public String getContactId() {
        return contactId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScore() { return score;}

    public void setScore(int score) {
        this.score = score;
    }
}
