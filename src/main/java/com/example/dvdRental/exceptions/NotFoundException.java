package com.example.dvdRental.exceptions;

import lombok.experimental.StandardException;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class NotFoundException extends AbstractAppError{
    public NotFoundException(String resourceName, Integer resourceId) {
        super("NOT_FOUND", String.format("Nie znaleziono %s o ID: %d", resourceName, resourceId));
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(super.toString());
    }
}
