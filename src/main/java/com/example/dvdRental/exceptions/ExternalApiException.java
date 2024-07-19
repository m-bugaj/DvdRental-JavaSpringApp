package com.example.dvdRental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ExternalApiException extends AbstractAppError {
    public ExternalApiException(String apiName, String url) {
        super("BAD_GATEWAY", String.format("Bład w nawiązywaniu połączenia z api o nazwie: %s. URL: %s", apiName, url));
    }

    @Override
    public ResponseEntity<String> handleException() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(super.toString());
    }
}
