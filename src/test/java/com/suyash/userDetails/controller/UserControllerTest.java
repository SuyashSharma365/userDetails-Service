package com.suyash.userDetails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyash.userDetails.entities.UserInfoDto;
import com.suyash.userDetails.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateOrUpdateUser() throws Exception {

        UserInfoDto dto = UserInfoDto.builder()
                .userId("USR123")
                .firstName("Suyash")
                .lastName("Sharma")
                .phoneNumber(9999999999L)
                .email("suyash@gmail.com")
                .build();

        when(userService.createOrUpdateUser(any(UserInfoDto.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/user/v1/createUpdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value("USR123"));
    }

    @Test
    void shouldGetUser() throws Exception {

        UserInfoDto dto = UserInfoDto.builder()
                .userId("USR123")
                .firstName("Suyash")
                .lastName("Sharma")
                .phoneNumber(9999999999L)
                .email("suyash@gmail.com")
                .build();

        when(userService.getUser(any(UserInfoDto.class)))
                .thenReturn(dto);

        mockMvc.perform(get("/user/v1/getUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value("USR123"));
    }
}