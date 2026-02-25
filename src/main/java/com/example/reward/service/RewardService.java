package com.example.reward.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.reward.entity.Transaction;
import com.example.reward.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RewardService {

	private final TransactionRepository transactionRepository;

	public int calculatePoints(double amount) {

		int points = 0;

		if (amount > 100) {
			points += (int) ((amount - 100) * 2);
			points += 50;
		} else if (amount > 50) {
			points += (int) (amount - 50);
		}

		return points;
	}

	// Monthly reward calculation (last 3 months)
	public Map<String, Integer> getMonthlyRewards(Long userId) {

		LocalDate now = LocalDate.now();
		LocalDate threeMonthsAgo = now.minusMonths(3);

		List<Transaction> transactions = transactionRepository.findByUserIdAndTransactionDateAfter(userId,
				threeMonthsAgo);

		Map<String, Integer> monthlyRewards = new LinkedHashMap<>();

		for (Transaction tx : transactions) {

			Month month = tx.getTransactionDate().getMonth();
			String monthName = month.toString();
			monthlyRewards.put(monthName, monthlyRewards.getOrDefault(monthName, 0) + tx.getRewardPoints());
		}

		return monthlyRewards;
	}

	// Total reward calculation

	public int getTotalRewards(Long userId) {

		List<Transaction> transactions = transactionRepository.findByUserId(userId);

		return transactions.stream().mapToInt(Transaction::getRewardPoints).sum();
	}
}
