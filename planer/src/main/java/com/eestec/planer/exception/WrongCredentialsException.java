package com.eestec.planer.exception;

public class WrongCredentialsException extends RuntimeException {
    public WrongCredentialsException(String username) {
        super("Unsuccessful login attempt for username: " + username);
    }
}
