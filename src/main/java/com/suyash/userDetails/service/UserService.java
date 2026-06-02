package com.suyash.userDetails.service;

import com.suyash.userDetails.entities.UserInfo;
import com.suyash.userDetails.entities.UserInfoDto;
import com.suyash.userDetails.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfoDto createOrUpdateUser(UserInfoDto userInfoDto){


        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .map(user -> {
                    user.setUserId(userInfoDto.getUserId());
                    user.setFirstName(userInfoDto.getFirstName());
                    user.setLastName(userInfoDto.getLastName());
                    user.setEmail(userInfoDto.getEmail());
                    user.setPhoneNumber(userInfoDto.getPhoneNumber());

                    return userRepository.save(user);

                })
                .orElseGet(()-> userRepository.save(userInfoDto.toEntity()));

        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail()
        );
    }

    public UserInfoDto getUser(UserInfoDto userInfoDto){
        UserInfo userInfo = userRepository.findByUserId(userInfoDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserInfoDto(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail()
        );
    }


}
