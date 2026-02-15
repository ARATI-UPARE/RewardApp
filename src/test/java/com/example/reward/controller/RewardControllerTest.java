package com.example.reward.controller;

import com.example.reward.service.RewardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    void testGetRewards() throws Exception {

        when(rewardService.getRewards("John"))
                .thenReturn(Map.of(
                        "customer", "John",
                        "totalPoints", 100
                ));

        mockMvc.perform(get("/rewards/John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer").value("John"))
                .andExpect(jsonPath("$.totalPoints").value(100));
    }
}
