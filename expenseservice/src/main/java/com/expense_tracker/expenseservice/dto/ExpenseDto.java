package com.expense_tracker.expenseservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExpenseDto {
    private String amount;
    private String merchant;
    private String currency;
    private String bank;
    private String transactionType;
    private String accountNumberMasked;
    private String transactionDate;
    private String transactionTime;
    private String paymentMethod;
    private String transactionCategory;
    private String transactionId;
    private Long userId;
    private String externalId;
}
