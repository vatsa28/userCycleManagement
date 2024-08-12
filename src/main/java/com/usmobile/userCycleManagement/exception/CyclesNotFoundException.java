package com.usmobile.userCycleManagement.exception;

/**
 * This class is used to handle Cycles Not Found exception.
 */
public class CyclesNotFoundException extends RuntimeException {
    public CyclesNotFoundException(String message) {
        super(message);
    }
}
