package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DataAccessException extends AbstractAppError {
    public DataAccessException(String message) {
        super("DATA_ACCESS_ERROR", message);
    }

    public DataAccessException(String message, Throwable cause) {
        super("DATA_ACCESS_ERROR", message + " - " + cause.toString());
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(super.toString());
    }
}
