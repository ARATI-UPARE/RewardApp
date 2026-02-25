package com.example.reward.service;


import java.time.LocalDate;
import org.springframework.stereotype.Service;
import com.example.reward.entity.Transaction;
import com.example.reward.entity.User;
import com.example.reward.repository.TransactionRepository;
import com.example.reward.repository.UserRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final RewardService rewardService;
    

    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository,
			RewardService rewardService) {
		super();
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
		this.rewardService = rewardService;
	}


	public Transaction addTransaction(Long userId, Double amount) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        int points = rewardService.calculatePoints(amount);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDate.now());
        transaction.setRewardPoints(points);
        transaction.setUser(user);
      
        return transactionRepository.save(transaction);
    }
}