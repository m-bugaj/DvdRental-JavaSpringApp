package com.example.dvdRental.api.model.post;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.StoreDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class PostCustomerDTO {
//    private Integer customerId;
    private Integer storeId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer addressId;
    private Boolean activebool;
//    private LocalDate createDate;
//    private Timestamp lastUpdate;
    private Integer active;
}
