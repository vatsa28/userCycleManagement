package com.usmobile.userCycleManagement.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.exception.UserNotFoundException;
import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {

        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@gmail.com", "password123");
        UserResponse response = new UserResponse("66b7e4843b43490f835cfaac", "John", "Doe", "john.doe@gmail.com");

        given(userService.createUser(any(CreateUserRequest.class))).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testCreateUserAlreadyPresent() throws Exception {

        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@gmail.com", "password123");

        given(userService.createUser(any(CreateUserRequest.class)))
                .willThrow(new UserAlreadyPresentException("User with email john.doe@gmail.com already exists"));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(String.format("User with email %s already exists", request.getEmail())));
    }

    @Test
    public void testUpdateUser() throws Exception {

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac","John2", "Doe2", "john.doe2@gmail.com");
        UserResponse response = new UserResponse("66b7e4843b43490f835cfaac", "John2", "Doe2", "john.doe2@gmail.com");

        given(userService.updateUser(any(UpdateUserRequest.class))).willReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void testUpdateUserNotFound() throws Exception {

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac","John2", "Doe2", "john.doe2@gmail.com");

        given(userService.updateUser(any(UpdateUserRequest.class)))
                .willThrow(new UserNotFoundException(String.format("User with userId %s doesn't exist", request.getUserId())));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(String.format("User with userId %s doesn't exist", request.getUserId())));
    }

    @Test
    public void testUpdateUserAlreadyExistsWithEmailId() throws Exception {

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac","John2", "Doe2", "john.doe2@gmail.com");

        given(userService.updateUser(any(UpdateUserRequest.class)))
                .willThrow(new UserAlreadyPresentException(String.format("User with email %s already exists with new email", request.getEmail())));

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/updateUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(String.format("User with email %s already exists with new email", request.getEmail())));
    }

}
