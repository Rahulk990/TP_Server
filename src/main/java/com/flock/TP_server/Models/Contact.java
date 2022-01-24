package com.flock.TP_server.models;


import javax.validation.constraints.*;

public class Contact {
    @Positive(message="Invalid UserId")
    private Integer userId;
    @Size(min=10, max=10, message = "Invalid Contact Id")
    private String contactId;
    @NotBlank(message="Name should not be empty")
    private String fullName;
    @Email(message="Invalid Email")
    private String email;
    private String address;
    @Pattern(regexp="^[0-9]*$", message="Invalid Phone Number")
    private String phoneNumber;
    @PositiveOrZero(message="Invalid Score")
    private Integer score;

    public Contact() {
    }

    public Contact(Integer userId, String contactId, String fullName, String email, String address, String phoneNumber, Integer score) {
        this.userId = userId;
        this.contactId = contactId;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.score = score;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}