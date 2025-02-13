package com.example.flight_tracker.exceptions;

public class VerificationCodeExpiredException extends RuntimeException {

    public VerificationCodeExpiredException(String message) {
        super(message);
    }

    public VerificationCodeExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
