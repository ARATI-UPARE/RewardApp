package com.example.reward.service;

import com.example.reward.model.Transaction;
import com.example.reward.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RewardServiceTest {

    private TransactionRepository repository;
    private RewardService rewardService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(TransactionRepository.class);
        rewardService = new RewardService(repository);
    }

    @Test
    void testCalculateRewardsForCustomer() {

        List<Transaction> transactions = List.of(
                new Transaction(1L, "John", 120.0, LocalDate.of(2025, 12, 5)),
                new Transaction(2L, "John", 75.0, LocalDate.of(2025, 12, 15))
        );

        when(repository.findByCustomerName("John")).thenReturn(transactions);

        Map<String, Object> result = rewardService.getRewards("John");

        assertEquals("John", result.get("customer"));
        assertTrue((Integer) result.get("totalPoints") > 0);
    }

    @Test
    void testZeroRewardsUnder50() {

        List<Transaction> transactions = List.of(
                new Transaction(1L, "John", 40.0, LocalDate.of(2025, 12, 5))
        );

        when(repository.findByCustomerName("John")).thenReturn(transactions);

        Map<String, Object> result = rewardService.getRewards("John");

        assertEquals(0, result.get("totalPoints"));
    }
}
