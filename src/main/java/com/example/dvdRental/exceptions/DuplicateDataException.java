package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DuplicateDataException extends AbstractAppError {
    public DuplicateDataException(String entityName, String dataName, String dataValue, Integer resourceId) {
        super("CONFLICT", String.format("Zduplikowany %s. Pole %s: %s juz istnieje. ID: %d", entityName, dataName, dataValue, resourceId));
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(super.toString());
    }
}
