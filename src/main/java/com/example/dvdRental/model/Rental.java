package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_generator")
    @SequenceGenerator(
            name = "rental_generator",
            sequenceName = "rental_rental_id_seq",
            allocationSize = 1
    )
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(
            name = "rental_date",
            nullable = false
    )
    private Timestamp rentalDate;

    @ManyToOne
    @JoinColumn(
            name = "inventory_id",
            nullable = false
    )
    private Inventory inventory;

//    @ManyToOne
//    @JoinColumn(
//            name = "customer_id",
//            nullable = false
//    )
//    private Customer customer;

    @OneToOne
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private Customer customer;

    @Column(
            name = "return_date",
            nullable = false
    )
    private Timestamp returnDate;

    @ManyToOne
    @JoinColumn(
            name = "staff_id",
            nullable = false
    )
    private Staff staff;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "rental")
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();



//    @OneToMany(mappedBy = "store")
//    @Builder.Default
//    private List<Customer> customers = new ArrayList<>();

}
