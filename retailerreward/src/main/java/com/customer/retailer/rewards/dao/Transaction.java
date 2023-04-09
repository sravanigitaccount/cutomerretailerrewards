/**
 * 
 */
package com.customer.retailer.rewards.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private Long id;
    private LocalDate date;
    private BigDecimal amount;
    private Long customerId;

    public Transaction(Long id, LocalDate date, BigDecimal amount, Long customerId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}