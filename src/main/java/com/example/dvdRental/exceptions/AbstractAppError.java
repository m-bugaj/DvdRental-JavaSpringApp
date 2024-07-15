package com.example.dvdRental.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractAppError extends Exception {
    private final String code;
    private final String message;

    @Override
    public String toString(){
        return String.format("%s %s", code, message);
    }

    public abstract ResponseEntity<String> handleException();
}
