package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.exception.UserNotFoundException;
import com.usmobile.userCycleManagement.pojo.CreateUserRequest;
import com.usmobile.userCycleManagement.pojo.UpdateUserRequest;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import static org.mockito.Mockito.verify;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void createUser() {

        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@example.com", "password123");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId("66b7e4843b43490f835cfaac"); // Simulate saved entity with an ID
            return user;
        });

        // Act
        UserResponse response = userService.createUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("66b7e4843b43490f835cfaac", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("john.doe@example.com", response.getEmail());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void createUser_UserAlreadyPresentException() {
        // Arrange
        CreateUserRequest request = new CreateUserRequest("John", "Doe", "john.doe@example.com", "password123");
        User existingUser = new User("Existing", "User", "john.doe@example.com", "existingPassword");

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));

        // Act & Assert
        assertThrows(UserAlreadyPresentException.class, () -> userService.createUser(request));
    }

    @Test
    public void updateUser(){

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac", "John", "Doe", "john.doe.new@example.com");
        User existingUser = new User("Existing", "User", "john.doe@example.com", "existingPassword");
        existingUser.setId("66b7e4843b43490f835cfaac");

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserResponse response = userService.updateUser(request);

        // Assert
        assertNotNull(response);
        assertEquals("66b7e4843b43490f835cfaac", response.getId());
        assertEquals("John", response.getFirstName());
        assertEquals("Doe", response.getLastName());
        assertEquals("john.doe.new@example.com", response.getEmail());
        verify(userRepository).save(existingUser);
    }

    @Test
    public void updateUser_userNotFoundwithId(){

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac", "John", "Doe", "john.doe.new@example.com");

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser(request));
    }

    @Test
    public void updateUser_userAlreadyExistWithEmail(){

        UpdateUserRequest request = new UpdateUserRequest("66b7e4843b43490f835cfaac", "John", "Doe", "john.doe.new@example.com");
        User existingUser = new User("Existing", "User", "john.doe.new@example.com", "existingPassword");
        existingUser.setId("66b7e4843b43490f835cfaac");

        when(userRepository.findById(request.getUserId())).thenReturn(Optional.of(existingUser));
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(UserAlreadyPresentException.class, () -> userService.updateUser(request));
    }
}