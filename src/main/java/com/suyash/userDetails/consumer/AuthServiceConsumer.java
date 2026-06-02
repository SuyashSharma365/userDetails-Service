package com.suyash.userDetails.consumer;

import com.suyash.userDetails.entities.UserInfoDto;
import com.suyash.userDetails.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

    @Autowired
   UserService userService;


    @KafkaListener( topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(UserInfoDto eventData){
        try{
//            System.out.println(eventData);
            userService.createOrUpdateUser(eventData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
