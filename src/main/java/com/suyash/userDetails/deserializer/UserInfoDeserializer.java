package com.suyash.userDetails.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyash.userDetails.entities.UserInfoDto;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer <UserInfoDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public UserInfoDto deserialize(String topic,  byte[] data) {

        try {
            if (data == null) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();

            UserInfoDto user = null;
            user = objectMapper.readValue(data, UserInfoDto.class);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing UserInfo", e);
        }
    }

    @Override
    public void close() {
    }
}
