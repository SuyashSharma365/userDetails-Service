package com.suyash.userDetails.consumer;

import com.suyash.userDetails.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class AuthServiceConsumer {

    UserRepository userRepository;

    @Autowired
    AuthServiceConsumer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @KafkaListener( topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Object eventData){
        try{

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
