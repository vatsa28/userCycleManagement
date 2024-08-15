package com.usmobile.userCycleManagement.controller;

import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for User related APIs
 */
@RestController
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * API to create a new user
     * @param createUserRequest
     * @return ResponseEntity<UserResponse>
     */
    @PostMapping("/api/v1/createUser")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserResponse currUser = userService.createUser(createUserRequest);
        return new ResponseEntity<>(currUser, HttpStatus.CREATED);
    }

    /**
     * API to update an existing user
     * @param updateUserRequest
     * @return ResponseEntity<UserResponse>
     */
    @PutMapping("/api/v1/updateUser")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        UserResponse updUser = userService.updateUser(updateUserRequest);
        return new ResponseEntity<>(updUser, HttpStatus.OK);
    }

}
