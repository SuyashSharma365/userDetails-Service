package com.suyash.userDetails.repository;
import com.suyash.userDetails.entities.UserInfo;
import com.suyash.userDetails.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@DataJpaTest
//becase this i need to setup a database
// i dont want to go with H2
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldFindUserByUserId() {

        UserInfo user = UserInfo.builder()
                .userId("USR123")
                .firstName("Suyash")
                .lastName("Sharma")
                .phoneNumber(9999999999L)
                .email("suyash@gmail.com")
                .build();

        userRepository.save(user);

        Optional<UserInfo> result =
                userRepository.findByUserId("USR123");

        assertTrue(result.isPresent());
        assertEquals("USR123", result.get().getUserId());
    }
}