package com.rewardapp.entity;

import java.time.LocalDateTime;

public class RewardPoints {
    private Long id;
    private Long userId;
    private Integer totalPoints;
    private LocalDateTime lastUpdated;

    public RewardPoints(Long id, Long userId, Integer totalPoints, LocalDateTime lastUpdated) {
        this.id = id;
        this.userId = userId;
        this.totalPoints = totalPoints;
        this.lastUpdated = lastUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}