package com.customer.retailer.rewards.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRewardPoints() throws Exception {
        // Generate some random transaction data
        List<Transaction> transactions = TransactionGenerator.generateTransactions(10);

        // Save the transactions to the database
        transactionRepository.saveAll(transactions);

        // Call the RESTful endpoint with the specified customer ID
        mockMvc.perform(MockMvcRequestBuilders.get("/rewards/customer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPoints").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPoints[*].month").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.monthlyPoints[*].points").isNumber());
    }
}