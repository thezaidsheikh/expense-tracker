package com.expense_tracker.userservice.consumer;

import com.expense_tracker.userservice.entities.UserInfoDto;
import com.expense_tracker.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {
    private UserRepository userRepository;

    @Autowired
    AuthServiceConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData) {
        try{
            System.out.println(eventData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
