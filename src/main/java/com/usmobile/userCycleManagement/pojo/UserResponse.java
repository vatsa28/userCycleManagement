package com.usmobile.userCycleManagement.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * Response POJO for User requests
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}
