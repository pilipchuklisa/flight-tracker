package com.example.flight_tracker.exceptions;

public class AccountAlreadyVerifiedException extends RuntimeException {

    public AccountAlreadyVerifiedException(String message) {
        super(message);
    }

    public AccountAlreadyVerifiedException(String message, Throwable cause) {
        super(message, cause);
    }
}
