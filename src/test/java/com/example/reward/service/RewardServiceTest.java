package com.example.reward.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Test
    void testAbove100() {
        assertEquals(90, rewardService.calculatePoints(new BigDecimal("120.00")));
    }

    @Test
    void testBetween50And100() {
        assertEquals(0, rewardService.calculatePoints(new BigDecimal("70")));
    }

    @Test
    void testBelow50() {
        assertEquals(0, rewardService.calculatePoints(new BigDecimal("40")));
    }
}