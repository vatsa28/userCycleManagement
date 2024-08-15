package com.usmobile.userCycleManagement.exception;

/**
 * This class is used to handle User Already Present exception.
 */
public class UserAlreadyPresentException extends RuntimeException{

        public UserAlreadyPresentException(String message) {
            super(message);
        }
}
