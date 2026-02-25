package com.example.reward.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.reward.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserIdAndTransactionDateAfter(Long userId, LocalDate date);
    List<Transaction> findByUserId(Long userId);
}