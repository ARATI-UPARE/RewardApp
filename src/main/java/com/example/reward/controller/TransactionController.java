package com.example.reward.controller;

import com.example.reward.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/{userId}")
    public ResponseEntity<?> addTransaction(
            @PathVariable Long userId,
            @RequestParam Double amount) {

        return ResponseEntity.ok(
                transactionService.addTransaction(userId, amount));
    }
}