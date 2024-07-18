package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Staff;
import com.example.dvdRental.model.Store;

public class StaffConverter {
    public static StaffDTO toDTO(Staff staff) {
        AddressDTO addressDTO = AddressConverter.toDTO(staff.getAddress());
//        StoreDTO storeDTO = StoreConverter.toDTO(staff.getStore());
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setStaffId(staff.getStaffId());
        staffDTO.setFirstName(staff.getFirstName());
        staffDTO.setLastName(staff.getLastName());
        staffDTO.setAddress(addressDTO);
        staffDTO.setEmail(staff.getEmail());
        staffDTO.setStoreId(staffDTO.getStoreId());
        staffDTO.setActive(staff.getActive());
        staffDTO.setUsername(staff.getUsername());
        staffDTO.setPassword(staff.getPassword());
        staffDTO.setLastUpdate(staff.getLastUpdate());
        staffDTO.setPicture(staff.getPicture());

        return staffDTO;
    }

    public static Staff toEntity(StaffDTO staffDTO) {
        Address address = AddressConverter.toEntity(staffDTO.getAddress());
//        Store store = StoreConverter.toEntity(staffDTO.getStore());
        Staff staff = new Staff();
        staff.setStaffId(staffDTO.getStaffId());
        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setAddress(address);
        staff.setEmail(staffDTO.getEmail());
        staff.setStoreId(staffDTO.getStoreId());
        staff.setActive(staffDTO.getActive());
        staff.setUsername(staffDTO.getUsername());
        staff.setPassword(staffDTO.getPassword());
        staff.setLastUpdate(staffDTO.getLastUpdate());
        staff.setPicture(staffDTO.getPicture());

        return staff;
    }
}
