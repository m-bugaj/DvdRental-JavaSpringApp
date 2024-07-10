package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_generator")
    @SequenceGenerator(
            name = "payment_generator",
            sequenceName = "payment_payment_id_seq",
            allocationSize = 1
    )
    @Column(name = "payment_id")
    private Integer paymentId;

    @ManyToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;

    @ManyToOne
    @JoinColumn(
            name = "staff_id",
            nullable = false
    )
    private Staff staff;

    @ManyToOne
    @JoinColumn(
            name = "rental_id",
            nullable = false
    )
    private Rental rental;

    @Column(
            precision = 5,
            scale = 2,
            nullable = false
    )
    private BigDecimal amount;

    @Column(
            name = "payment_date",
            nullable = false
    )
    private Timestamp paymentDate;

}
