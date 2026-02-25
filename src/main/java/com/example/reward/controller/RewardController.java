package com.example.reward.controller;

import com.example.reward.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
@RequiredArgsConstructor
public class RewardController {

	private final RewardService rewardService;

	@GetMapping("/total/{userId}")
	public ResponseEntity<Integer> getTotalRewards(@PathVariable Long userId) {
		int total = rewardService.getTotalRewards(userId);
		return ResponseEntity.ok(total);
	}

	@GetMapping("/{userId}/monthly")
	public ResponseEntity<?> getMonthlyRewards(@PathVariable Long userId) {

		return ResponseEntity.ok(rewardService.getMonthlyRewards(userId));
	}
}