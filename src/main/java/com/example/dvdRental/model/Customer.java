package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.SmallIntJdbcType;

import java.beans.ConstructorProperties;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(
            name = "customer_generator",
            sequenceName = "customer_customer_id_seq",
            allocationSize = 1
    )
    @Column(name = "customer_id")
    private Integer customerId;

    @ManyToOne
    @JoinColumn(
            name = "store_id",
            nullable = false
    )
    private Store store;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    private String email;

    @ManyToOne
    @JoinColumn(
            name = "address_id",
            nullable = false
    )
    private Address address;

    @Builder.Default
    @Column(
            name = "activebool",
            nullable = false
    )
    private Boolean activeBool = true;

    @Column(
            name = "create_date",
            nullable = false
    )
    @Builder.Default
    private LocalDate createDate = LocalDate.now();

    @Builder.Default
    @Column(name =  "last_update")
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    private Integer active;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Rental> rentals = new ArrayList<>();

//    @OneToOne(
//            mappedBy = "customer"
////            ,
////            cascade = CascadeType.ALL,
////            orphanRemoval = true
//    )
//    private Rental rental;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private String gender = "undefined";


}
