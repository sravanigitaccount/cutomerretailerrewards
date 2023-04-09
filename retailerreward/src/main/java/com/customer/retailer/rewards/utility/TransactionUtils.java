package com.customer.retailer.rewards.utility;

import java.math.BigDecimal;
import java.util.List;

public class TransactionUtils {
    public static BigDecimal calculateTotalAmount(List<Transaction> transactions) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            totalAmount = totalAmount.add(transaction.getAmount());
        }
        return totalAmount;
    }
}