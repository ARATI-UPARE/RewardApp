package com.rewardapp.entity;

import java.util.Date;

public class Transaction {
    private String id;
    private String userId;
    private double amount;
    private int points;
    private Date date;

    public Transaction(String id, String userId, double amount, int points, Date date) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.points = points;
        this.date = date;
    }
    
    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}