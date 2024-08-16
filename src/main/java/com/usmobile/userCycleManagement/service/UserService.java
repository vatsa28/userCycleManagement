package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.controller.UserController;
import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.exception.UserNotFoundException;
import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for all the user related operations
 */
@Service
public class UserService {

    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    /**
     *
     * @param createUserRequest
     * @return UserResponse
     * @throws UserAlreadyPresentException when user with email already exists
     */
    public UserResponse createUser(CreateUserRequest createUserRequest){

        logger.info("IN::UserService:createUser()");

        Optional<User> optionalUser = userRepository.findByEmail(createUserRequest.getEmail());
        if (optionalUser.isPresent()) {
            logger.error(String.format("User with email %s already exists", createUserRequest.getEmail()));
            throw new UserAlreadyPresentException(String.format("User with email %s already exists", createUserRequest.getEmail()));
        }

        User newUser = new User(
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getEmail(),
                passwordEncoder.encode(createUserRequest.getPassword()));
        User savedUser = userRepository.save(newUser);
        logger.info("User saved successfully");

        logger.info("OUT::UserService:createUser()");
        return new UserResponse(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }

    /**
     *
     * @param updateUserRequest
     * @return UserResponse
     * @throws UserNotFoundException when user is not found to update
     * @throws UserAlreadyPresentException when user with new email already exists
     */
    public UserResponse updateUser(UpdateUserRequest updateUserRequest){
        logger.info("IN::UserService:updateUser()");

        Optional<User> optionalUser = userRepository.findById(updateUserRequest.getUserId());

        if (optionalUser.isEmpty()) {
            logger.error(String.format("User with userId %s doesn't exist", updateUserRequest.getUserId()));
            throw new UserNotFoundException(String.format("User with userId %s doesn't exist", updateUserRequest.getUserId()));
        }

        Optional<User> userWithEmail = userRepository.findByEmail(updateUserRequest.getEmail());
        if (userWithEmail.isPresent()) {
            logger.error(String.format("User with email %s already exists with new email", updateUserRequest.getEmail()));
            throw new UserAlreadyPresentException(String.format("User with email %s already exists with new email", updateUserRequest.getEmail()));
        }

        User currUser = optionalUser.get();
        currUser.setFirstName(updateUserRequest.getFirstName());
        currUser.setLastName(updateUserRequest.getLastName());
        currUser.setEmail(updateUserRequest.getEmail());
        User updatedUser = userRepository.save(currUser);
        logger.info("User updated successfully");

        logger.info("OUT::UserService:updateUser()");
        return new UserResponse(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail());
    }
}
