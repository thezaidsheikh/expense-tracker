package com.auth.App.eventProducer;

import com.auth.App.model.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoProducer {
    private final KafkaTemplate<String, UserInfoDto> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String TOPIC_NAME;

    @Autowired
    public UserInfoProducer(KafkaTemplate<String, UserInfoDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEventToKafka(UserInfoDto eventData) {
        try{
            org.springframework.messaging.Message<UserInfoDto> message = MessageBuilder.withPayload(eventData).setHeader(KafkaHeaders.TOPIC,TOPIC_NAME).build();
            this.kafkaTemplate.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
