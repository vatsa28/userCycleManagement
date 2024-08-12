package com.usmobile.userCycleManagement.exception;
/**
 * This class is used to handle User Not Found exception.
 */
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message) {
        super(message);
    }
}
