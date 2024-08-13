package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.exception.UserNotFoundException;
import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
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

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    /**
     *
     * @param createUserRequest
     * @return UserResponse
     */
    public UserResponse createUser(CreateUserRequest createUserRequest){

        Optional<User> optionalUser = userRepository.findByEmail(createUserRequest.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyPresentException("User with email " + createUserRequest.getEmail() + " already exists");
        }

        User newUser = new User(
                createUserRequest.getFirstName(),
                createUserRequest.getLastName(),
                createUserRequest.getEmail(),
                passwordEncoder.encode(createUserRequest.getPassword()));
        User savedUser = userRepository.save(newUser);
        return new UserResponse(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }

    /**
     *
     * @param updateUserRequest
     * @return UserResponse
     */
    public UserResponse updateUser(UpdateUserRequest updateUserRequest){
        Optional<User> optionalUser = userRepository.findById(updateUserRequest.getUserId());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with userId " + updateUserRequest.getUserId() + " doesn't exist");
        }

        Optional<User> userWithEmail = userRepository.findByEmail(updateUserRequest.getEmail());
        if (userWithEmail.isPresent()) {
            throw new UserAlreadyPresentException("User with email " + updateUserRequest.getEmail() + " already exists");
        }

        User currUser = optionalUser.get();
        currUser.setFirstName(updateUserRequest.getFirstName());
        currUser.setLastName(updateUserRequest.getLastName());
        currUser.setEmail(updateUserRequest.getEmail());
        User updatedUser = userRepository.save(currUser);
        return new UserResponse(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail());
    }
}
