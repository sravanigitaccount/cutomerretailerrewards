package com.customer.retailer.rewards.utility;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TransactionGenerator {
    private static final int MIN_AMOUNT = 50;
    private static final int MAX_AMOUNT = 500;
    private static final int POINTS_PER_DOLLAR_OVER_100 = 2;
    private static final int POINTS_PER_DOLLAR_BETWEEN_50_100 = 1;
    private static final LocalDate START_DATE = LocalDate.of(2023, 1, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 3, 31);

    public static List<Transaction> generateTransactions(int numCustomers) {
        Random random = new Random();
        List<Transaction> transactions = new ArrayList<>();

        for (int customerId = 1; customerId <= numCustomers; customerId++) {
            LocalDate transactionDate = START_DATE;
            while (!transactionDate.isAfter(END_DATE)) {
                int numTransactions = random.nextInt(10) + 1; // Generate 1 to 10 transactions per day
                for (int i = 0; i < numTransactions; i++) {
                    BigDecimal transactionAmount = BigDecimal.valueOf(random.nextInt(MAX_AMOUNT - MIN_AMOUNT + 1) + MIN_AMOUNT);
                    int rewardPoints = calculateRewardPoints(transactionAmount);
                    transactions.add(new Transaction(customerId, transactionDate, transactionAmount, rewardPoints));
                }
                transactionDate = transactionDate.plusDays(1);
            }
        }

        return transactions;
    }

    private static int calculateRewardPoints(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(100)) > 0) {
            BigDecimal dollarsOver100 = amount.subtract(BigDecimal.valueOf(100));
            int pointsOver100 = dollarsOver100.multiply(BigDecimal.valueOf(POINTS_PER_DOLLAR_OVER_100)).intValue();
            int pointsBetween50And100 = 50 * POINTS_PER_DOLLAR_BETWEEN_50_100;
            return pointsOver100 + pointsBetween50And100;
        } else if (amount.compareTo(BigDecimal.valueOf(50)) > 0) {
            BigDecimal dollarsBetween50And100 = amount.subtract(BigDecimal.valueOf(50));
            return dollarsBetween50And100.multiply(BigDecimal.valueOf(POINTS_PER_DOLLAR_BETWEEN_50_100)).intValue();
        } else {
            return 0;
        }
    }
}
