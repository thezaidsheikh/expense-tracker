package com.expense_tracker.expenseservice.deserializer;

import com.expense_tracker.expenseservice.dto.ExpenseDto;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

public class ExpenseDeserializer implements Deserializer<ExpenseDto> {
    @Override
    public void close(){}

    @Override
    public void configure(Map<String, ?> configs, boolean isKey){}

    @Override
    public ExpenseDto deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto res = null;
        try{
            res = objectMapper.readValue(data, ExpenseDto.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

}
