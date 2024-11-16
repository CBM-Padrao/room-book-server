package com.project.roombook.exceptions;

public class BookingAlreadyExistsException extends RuntimeException{
    public BookingAlreadyExistsException(String message) {
        super(message);
    }
}