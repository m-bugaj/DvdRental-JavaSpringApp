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
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_generator")
    @SequenceGenerator(
            name = "store_generator",
            sequenceName = "store_store_id_seq",
            allocationSize = 1
    )
    @Column(name = "store_id")
    private Integer storeId;

    @Column(
            name = "manager_staff_id",
            nullable = false
    )
    private Integer managerStaffId;

    @ManyToOne
    @JoinColumn(
            name = "address_id",
            nullable = false
    )
    private Address address;

    @Builder.Default
    @Column(
            name =  "last_update",
            nullable = false
    )
    private Timestamp lastUpdate = Timestamp.valueOf(LocalDateTime.now());

    @OneToOne(mappedBy = "store")
    private Staff staff;

//    @OneToMany(mappedBy = "store")
//    private List<Inventory> inventories = new ArrayList<>();

}
