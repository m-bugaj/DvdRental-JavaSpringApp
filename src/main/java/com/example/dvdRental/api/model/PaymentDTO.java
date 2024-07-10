package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class PaymentDTO {
    private Integer paymentId;
    private CustomerDTO customer;
    private StaffDTO staff;
    private RentalDTO rental;
    private BigDecimal amount;
    private Timestamp paymentDate;
}
