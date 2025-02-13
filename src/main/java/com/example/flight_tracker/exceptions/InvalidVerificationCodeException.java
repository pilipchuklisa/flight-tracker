package com.example.flight_tracker.exceptions;

public class InvalidVerificationCodeException extends RuntimeException {

    public InvalidVerificationCodeException(String message) {
        super(message);
    }

    public InvalidVerificationCodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
