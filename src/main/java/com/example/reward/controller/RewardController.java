package com.example.reward.controller;

import com.example.reward.service.RewardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService service;

    public RewardController(RewardService service) {
        this.service = service;
    }

    @GetMapping("/{customerName}")
    public Map<String, Object> getRewards(@PathVariable String customerName) {
        return service.getRewards(customerName);
    }
}
