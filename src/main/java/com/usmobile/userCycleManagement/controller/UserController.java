package com.usmobile.userCycleManagement.controller;

import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for User related APIs
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        logger.info("IN:UserController:createUser()");

        UserResponse currUser = userService.createUser(createUserRequest);

        logger.info("OUT::UserController:createUser()");
        return new ResponseEntity<>(currUser, HttpStatus.CREATED);
    }

    /**
     * API to update an existing user
     * @param updateUserRequest
     * @return ResponseEntity<UserResponse>
     */
    @PutMapping("/api/v1/updateUser")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        logger.info("IN::UserController:updateUser()");

        UserResponse updUser = userService.updateUser(updateUserRequest);

        logger.info("OUT::UserController:updateUser()");
        return new ResponseEntity<>(updUser, HttpStatus.OK);
    }

}
