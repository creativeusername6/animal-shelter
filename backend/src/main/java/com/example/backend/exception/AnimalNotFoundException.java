package com.example.backend.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message, Long id) {
        super(message + id);
    }
}
