package com.suyash.userDetails.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyash.userDetails.entities.UserInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDeserializer implements Deserializer <UserInfo> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public UserInfo deserialize(String topic,  byte[] data) {

        try {
            if (data == null) {
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();

            UserInfo user = null;
            user = objectMapper.readValue(data, UserInfo.class);
            return user;
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing UserInfo", e);
        }
    }

    @Override
    public void close() {
    }

}
