package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.exception.UserNotFoundException;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public UserResponse createUser(String firstName, String lastName, String email, String password){

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            throw new UserAlreadyPresentException("User with email " + email + " already exists");
        }

        User newUser = new User(firstName, lastName, email, passwordEncoder.encode(password));
//        User newUser = new User(firstName, lastName, email, password);
        User savedUser = userRepository.save(newUser);
        return new UserResponse(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }

    public UserResponse updateUser(String userId, String firstName, String lastName, String email){
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User with userId " + userId + " doesn't exist");
        }

        Optional<User> userWithEmail = userRepository.findByEmail(email);
        if (userWithEmail.isPresent()) {
            throw new UserAlreadyPresentException("User with email " + email + " already exists");
        }

        User currUser = optionalUser.get();
        currUser.setFirstName(firstName);
        currUser.setLastName(lastName);
        currUser.setEmail(email);
        User updatedUser = userRepository.save(currUser);
        return new UserResponse(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail());
    }
}
