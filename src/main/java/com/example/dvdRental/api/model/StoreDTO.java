package com.example.dvdRental.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Data
public class StoreDTO {
    private Integer storeId;
    private Integer managerStaffId;
    private AddressDTO address;
    private Timestamp lastUpdate;
}
