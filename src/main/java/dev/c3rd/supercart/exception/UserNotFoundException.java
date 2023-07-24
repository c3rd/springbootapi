package dev.c3rd.supercart.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(String message) {
        super(message);
    }
}
