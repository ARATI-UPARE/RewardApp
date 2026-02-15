package com.example.reward.service;

import com.example.reward.model.Transaction;
import com.example.reward.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;

@Service
public class RewardService {

    private final TransactionRepository repository;

    public RewardService(TransactionRepository repository) {
        this.repository = repository;
    }
    
    
    public Map<String, Object> getRewards(String customerName) {

        List<Transaction> transactions = repository.findByCustomerName(customerName);

        Map<Month, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Transaction t : transactions) {
            int points = calculatePoints(t.getAmount());
            Month month = t.getTransactionDate().getMonth();

            monthlyPoints.put(month,
                    monthlyPoints.getOrDefault(month, 0) + points);

            totalPoints += points;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("customer", customerName);
        result.put("monthlyPoints", monthlyPoints);
        result.put("totalPoints", totalPoints);

        return result;
    }


    private int calculatePoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points += (amount - 100) * 2;
            points += 50;
        } else if (amount > 50) {
            points += (amount - 50);
        }

        return points;
    }

}
