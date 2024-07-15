package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;

@Component
public class ApiExceptionHandler {
    public static ResponseEntity<?> apiHandleException(Exception e) {
        if (e instanceof AbstractAppError appError) {
            return appError.handleException();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unknown error");
    }
}
