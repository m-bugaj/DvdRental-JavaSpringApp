package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DisposableEmailException extends AbstractAppError {
    public DisposableEmailException(String email, String resourceName) {
        super("INVALID_DATA", String.format("Użyto tymczasowego e-maila: %s. Nie utworzono rekordu %s", email, resourceName));
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(super.toString());
    }
}
