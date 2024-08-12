package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserResponse createUser(String firstName, String lastName, String email, String password){
        User newUser = new User(firstName, lastName, email, password);
        User savedUser = userRepository.save(newUser);
        return new UserResponse(savedUser.getId(), savedUser.getFirstName(), savedUser.getLastName(), savedUser.getEmail());
    }

    public UserResponse updateUser(String userId, String firstName, String lastName, String email){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User currUser = optionalUser.get();
            currUser.setFirstName(firstName);
            currUser.setLastName(lastName);
            currUser.setEmail(email);
            User updatedUser = userRepository.save(currUser);
            return new UserResponse(updatedUser.getId(), updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getEmail());
        }
        return null;
    }
}
