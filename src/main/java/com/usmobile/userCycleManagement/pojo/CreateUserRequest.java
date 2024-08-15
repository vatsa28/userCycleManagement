package com.usmobile.userCycleManagement.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request POJO for creating a new user
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @Email
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
