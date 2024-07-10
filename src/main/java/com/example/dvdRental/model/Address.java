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
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_generator")
    @SequenceGenerator(
            name = "address_generator",
            sequenceName = "address_address_id_seq",
            allocationSize = 1
    )
    @Column(name =  "address_id")
    private Integer addressId;

    @Column(nullable = false)
    private String address;

    private String address2;

    @Column(nullable = false)
    private String district;

    @ManyToOne
    @JoinColumn(
            name = "city_id",
            nullable = false
    )
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(nullable = false)
    private String phone;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToMany(mappedBy = "address")
    @Builder.Default
    private List<Staff> Staffs = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    @Builder.Default
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "address")
    @Builder.Default
    private List<Customer> customers = new ArrayList<>();
}
