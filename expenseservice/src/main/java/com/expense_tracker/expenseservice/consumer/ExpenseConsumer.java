package com.expense_tracker.expenseservice.consumer;

import com.expense_tracker.expenseservice.dto.ExpenseDto;
import com.expense_tracker.expenseservice.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenseConsumer {
    private ExpenseService expenseService;

    @Autowired
    public ExpenseConsumer(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.ds-extract-event}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenToDSServiceExtractEvent(ExpenseDto data) {
        try {
            this.expenseService.createExpense(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
