package com.project.roombook.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException (String message) {
        super(message);
    }
}
