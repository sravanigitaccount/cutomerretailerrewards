package com.customer.retailer.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.customer.retailer.rewards.service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{customerId}/rewards")
    public ResponseEntity<Map<String, Object>> getRewardPointsByCustomerAndMonth(
            @PathVariable Long customerId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {
        if (startDate == null) {
            startDate = LocalDate.now().minusMonths(3).withDayOfMonth(1);
        }
        if (endDate == null) {
            endDate = LocalDate.now().withDayOfMonth(1).minusDays(1);
        }
        List<Transaction> transactions = transactionService.getTransactionsByCustomerIdAndDateRange(customerId, startDate, endDate);
        Map<String, Object> result = calculateRewardPointsByMonth(transactions);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private Map<String, Object> calculateRewardPointsByMonth(List<Transaction> transactions) {
        Map<String, Object> result = transactions.stream()
                .collect(Collectors.groupingBy(
                        transaction -> transaction.getTransactionDate().withDayOfMonth(1).toString(),
                        Collectors.summarizingInt(Transaction::getRewardPoints)
                ));
        BigDecimal totalRewardPoints = transactions.stream()
                .map(Transaction::getRewardPoints)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        result.put("totalRewardPoints", totalRewardPoints);
        return result;
    }
}