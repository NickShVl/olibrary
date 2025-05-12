package com.example.olibrary.exceptions;

public class AccessDenialException extends RuntimeException{
    public AccessDenialException(String message) {
        super(message);
    }
}
