package com.expense_tracker.expenseservice.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "expenses")
@Builder
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String amount;

    @Column(nullable = false, columnDefinition = "varchar(10) default 'INR'")
    private String currency;

    @Column(nullable = false)
    private String bank;

    @Column(nullable = false)
    private String externalId;

    @Column(nullable = false)
    private Long userId;

    @Column(columnDefinition = "varchar(10) default 'DEBIT'")
    private String transactionType;

    private String merchant;
    private String accountNumberMasked;
    private String transactionDate;
    private String transactionTime;
    private String paymentMethod;
    private String transactionCategory;
    private String transactionId;
    private Timestamp createdAt;

    @PrePersist
    @PreUpdate
    private void generateExternalId() {
        if(this.externalId == null) this.externalId = UUID.randomUUID().toString();
    }
}
