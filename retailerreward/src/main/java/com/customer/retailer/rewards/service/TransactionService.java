package com.customer.retailer.rewards.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.retailer.rewards.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransactionService {
    private static final BigDecimal REWARD_POINTS_RATE_1 = new BigDecimal("1");
    private static final BigDecimal REWARD_POINTS_RATE_2 = new BigDecimal("2");
    private static final BigDecimal MINIMUM_AMOUNT_FOR_REWARD_POINTS = new BigDecimal("50");
    private static final BigDecimal AMOUNT_RANGE_FOR_REWARD_POINTS = new BigDecimal("50");
    private static final BigDecimal MINIMUM_AMOUNT_FOR_REWARD_POINTS_RATE_2 = new BigDecimal("100");

    @Autowired
    private TransactionRepository transactionRepository;

    public int calculateRewardPoints(Transaction transaction) {
        BigDecimal amount = transaction.getAmount();
        int rewardPoints = 0;
        if (amount.compareTo(MINIMUM_AMOUNT_FOR_REWARD_POINTS) >= 0) {
            BigDecimal rewardPointsRate = amount.compareTo(MINIMUM_AMOUNT_FOR_REWARD_POINTS_RATE_2) >= 0 ?
                    REWARD_POINTS_RATE_2 : REWARD_POINTS_RATE_1;
            BigDecimal amountRange = amount.subtract(MINIMUM_AMOUNT_FOR_REWARD_POINTS);
            BigDecimal rewardPointsAmount = amountRange.divide(AMOUNT_RANGE_FOR_REWARD_POINTS).setScale(0, BigDecimal.ROUND_DOWN);
            rewardPoints = rewardPointsAmount.multiply(rewardPointsRate).intValue();
        }
        return rewardPoints;
    }

    public void saveTransaction(Transaction transaction) {
        int rewardPoints = calculateRewardPoints(transaction);
        transaction.setRewardPoints(rewardPoints);
        transactionRepository.save(transaction);
    }
}
