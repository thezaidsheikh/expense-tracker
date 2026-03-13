package com.expense_tracker.expenseservice.repository;

import com.expense_tracker.expenseservice.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserId(Long userId);
    List<Expense> findByUserIdAndCreatedAtBetween(Long userId, Timestamp startTime, Timestamp endTime);
    Optional<Expense> findByUserIdAndExternalId(Long userId, String externalId);
}
