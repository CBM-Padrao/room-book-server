package com.project.roombook.exceptions;

public class AuthenticationFailureException extends RuntimeException{
    public AuthenticationFailureException(String message) {
        super(message);
    }
}
