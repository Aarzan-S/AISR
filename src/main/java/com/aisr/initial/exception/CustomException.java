package com.aisr.initial.exception;

/**
 * This is the custom exception for handing exceptions in the project
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
