package com.suyash.userDetails.consumer;

import com.suyash.userDetails.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

    UserRepository userRepository;

    @Autowired
    AuthServiceConsumer(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @KafkaListener( topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Object eventData){
        try{
            System.out.println(eventData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
