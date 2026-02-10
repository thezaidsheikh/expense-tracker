package com.expense_tracker.userservice.deserializer;

import com.expense_tracker.userservice.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * This class is used to deserialize the UserInfoDto object from the byte array received from the Kafka topic.
 * It implements the Deserializer interface provided by Apache Kafka.
 * It uses the ObjectMapper class from the Jackson library to convert the byte array into a UserInfoDto object.
 * When AuthService sends the UserInfoDto object to the Kafka topic, it is serialized into a byte array.
 * This deserializer is used to convert the byte array back into a UserInfoDto object.
 */
public class UserInfoDeserializer implements Deserializer<UserInfoDto> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public UserInfoDto deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfoDto user = null;
        try{
            user = objectMapper.readValue(data, UserInfoDto.class);
            return user;
        }catch(Exception ex) {
            throw new RuntimeException("Failed to deserialize UserInfoDto", ex);
        }
    }

    @Override
    public void close() {
    }
}
