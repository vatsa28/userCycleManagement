package com.usmobile.userCycleManagement.service;

import com.usmobile.userCycleManagement.entity.User;
import com.usmobile.userCycleManagement.exception.UserAlreadyPresentException;
import com.usmobile.userCycleManagement.pojo.UserResponse;
import com.usmobile.userCycleManagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUserSuccess() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";
        User user = new User(firstName, lastName, email, encodedPassword);
        UserResponse userResponse = new UserResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponse result = userService.createUser(firstName, lastName, email, password);

        assertEquals(userResponse, result);
        verify(userRepository, times(1)).findByEmail(email);
        verify(passwordEncoder, times(1)).encode(password);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUserEmailAlreadyExists() {
        String firstName = "John";
        String lastName = "Doe";
        String email = "john.doe@example.com";
        String password = "password";
        User existingUser = new User(firstName, lastName, email, password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(existingUser));

        assertThrows(UserAlreadyPresentException.class, () -> userService.createUser(firstName, lastName, email, password));
    }
}