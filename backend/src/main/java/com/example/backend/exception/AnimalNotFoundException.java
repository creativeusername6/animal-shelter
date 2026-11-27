package com.example.backend.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(Long id) {
        final String message = "Could not find animal with ID: ";

        super(message + id);
    }
}
