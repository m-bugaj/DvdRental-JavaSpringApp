package com.example.dvdRental.model;

import jakarta.persistence.*;
import lombok.*;
import org.aspectj.lang.annotation.DeclareAnnotation;

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
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "staff_generator")
    @SequenceGenerator(
            name = "staff_generator",
            sequenceName = "staff_staff_id_seq",
            allocationSize = 1
    )
    @Column(name = "staff_id")
    private Integer staffId;

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

    @ManyToOne
    @JoinColumn(
            name = "address_id",
            nullable = false
    )
    private Address address;

    private String email;

    @ManyToOne
    @JoinColumn(
            name = "store_id",
            nullable = false
    )
    private Store store;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private String username;

    private String password;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    private byte[] picture;

    @OneToMany(mappedBy = "staff")
    @Builder.Default
    private List<Rental> rentals = new ArrayList<>();

    @OneToMany(mappedBy = "staff")
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();


}
