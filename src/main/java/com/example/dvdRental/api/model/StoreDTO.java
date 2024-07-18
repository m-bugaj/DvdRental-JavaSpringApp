package com.example.dvdRental.api.model;

import com.example.dvdRental.model.Staff;
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
    private StaffDTO managerStaff;
    private AddressDTO address;
    private Timestamp lastUpdate;
}
