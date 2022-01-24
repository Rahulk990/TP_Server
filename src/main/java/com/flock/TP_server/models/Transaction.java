package com.flock.TP_server.models;

public class Transaction {

    private Integer userId;
    private Long transactionId;
    private String transactionString;

    public Transaction() {}

    public Transaction(Integer userId, Long transactionId, String transactionString) {
        this.userId = userId;
        this.transactionId = transactionId;
        this.transactionString = transactionString;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionString() {
        return transactionString;
    }

    public void setTransactionString(String transactionString) {
        this.transactionString = transactionString;
    }
}
