package com.auth.App.serializer;

import com.auth.App.model.UserInfoDto;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    public byte[] serialize(String TOPIC_NAME, UserInfoDto data){
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        retVal = objectMapper.writeValueAsString(data).getBytes();
        return retVal;
    }

    @Override public void close() {
    }
}
