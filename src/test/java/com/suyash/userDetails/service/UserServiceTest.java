package com.suyash.userDetails.service;

import com.suyash.userDetails.entities.UserInfo;
import com.suyash.userDetails.entities.UserInfoDto;
import com.suyash.userDetails.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldConvertDtoToEntity() {

        UserInfoDto dto = UserInfoDto.builder()
                .userId("USR123")
                .firstName("Suyash")
                .lastName("Sharma")
                .phoneNumber(9999999999L)
                .email("suyash@gmail.com")
                .build();

        UserInfo entity = dto.toEntity();

        assertAll(
                () -> assertEquals("USR123", entity.getUserId()),
                () -> assertEquals("Suyash", entity.getFirstName()),
                () -> assertEquals("Sharma", entity.getLastName()),
                () -> assertEquals(9999999999L, entity.getPhoneNumber()),
                () -> assertEquals("suyash@gmail.com", entity.getEmail())
        );
    }
    @Test
    void shouldCreateNewUser() {

        UserInfoDto dto = new UserInfoDto(
                "123",
                "Suyash",
                "Sharma",
                9999999999L,
                "suyash@gmail.com"
        );

        UserInfo entity = dto.toEntity();

        when(userRepository.findByUserId("123"))
                .thenReturn(Optional.empty());

        when(userRepository.save(any(UserInfo.class)))
                .thenReturn(entity);

        UserInfoDto result = userService.createOrUpdateUser(dto);

        assertEquals("123", result.getUserId());
        assertEquals("Suyash", result.getFirstName());

        verify(userRepository).save(any(UserInfo.class));
    }

    @Test
    void shouldUpdateExistingUser() {

        UserInfo existingUser = new UserInfo();
        existingUser.setUserId("123");
        existingUser.setFirstName("Old");

        UserInfoDto dto = new UserInfoDto(
                "123",
                "New",
                "Sharma",
                9999999999L,
                "new@gmail.com"
        );

        when(userRepository.findByUserId("123"))
                .thenReturn(Optional.of(existingUser));

        when(userRepository.save(any(UserInfo.class)))
                .thenReturn(existingUser);

        UserInfoDto result = userService.createOrUpdateUser(dto);

        assertEquals("123", result.getUserId());

        verify(userRepository).findByUserId("123");
        verify(userRepository).save(any(UserInfo.class));
    }

    @Test
    void shouldGetUserSuccessfully() {

        UserInfo user = new UserInfo();
        user.setUserId("123");
        user.setFirstName("Suyash");
        user.setLastName("Sharma");
        user.setPhoneNumber(9999999999L);
        user.setEmail("suyash@gmail.com");

        UserInfoDto request = new UserInfoDto();
        request.setUserId("123");

        when(userRepository.findByUserId("123"))
                .thenReturn(Optional.of(user));

        UserInfoDto result = userService.getUser(request);

        assertEquals("123", result.getUserId());
        assertEquals("Suyash", result.getFirstName());

        verify(userRepository).findByUserId("123");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {

        UserInfoDto request = new UserInfoDto();
        request.setUserId("999");

        when(userRepository.findByUserId("999"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.getUser(request)
        );

        assertEquals("User not found", exception.getMessage());
    }
}