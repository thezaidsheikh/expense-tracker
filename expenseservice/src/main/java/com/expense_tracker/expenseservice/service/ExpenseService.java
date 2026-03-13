package com.expense_tracker.expenseservice.service;

import com.expense_tracker.expenseservice.dto.ExpenseDto;
import com.expense_tracker.expenseservice.entity.Expense;
import com.expense_tracker.expenseservice.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private ExpenseRepository expenseRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public boolean createExpense(ExpenseDto data) throws Exception {
        this.expenseRepository.save(objectMapper.convertValue(data, Expense.class));
        return true;
    }

    public boolean updateExpense(ExpenseDto data) throws Exception {
        Optional<Expense> expense = this.expenseRepository.findByUserIdAndExternalId(data.getUserId(), data.getExternalId());
        if(expense.isEmpty()) return false;
        this.expenseRepository.save(objectMapper.convertValue(data, Expense.class));
        return true;
    }

    public List<ExpenseDto> getExpenses(Long userId) throws Exception {
        List<Expense> expenses = this.expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenses, new TypeReference<List<ExpenseDto>>(){});
    }
}
