package com.example.dvdRental.util.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisifyResponse {
    private boolean format;
    private String domain;
    private boolean disposable;
    private boolean dns;
}
