package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InvalidDataException extends AbstractAppError {
    public InvalidDataException(String dataName) {
        super("INVALID_DATA", String.format("Niepoprawny format w: %s", dataName));
    }

    public InvalidDataException(String message, Throwable cause) {
        super("INVALID_DATA", message + " - " + cause.toString());
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(super.toString());
    }
}
