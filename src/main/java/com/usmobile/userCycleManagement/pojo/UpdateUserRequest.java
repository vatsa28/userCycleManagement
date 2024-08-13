package com.usmobile.userCycleManagement.pojo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request POJO to update user information
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "UserId is mandatory")
    private String userId;
    @NotBlank(message = "First Name is mandatory")
    private String firstName;
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
}
