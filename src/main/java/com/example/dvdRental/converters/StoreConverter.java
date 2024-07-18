package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Staff;
import com.example.dvdRental.model.Store;
import com.example.dvdRental.repositories.StaffRepository;
import com.example.dvdRental.repositories.StoreRepository;

import java.util.Optional;

public class StoreConverter {
    public static StaffRepository staffRepository;

    public StoreConverter(StaffRepository staffRepository) {
        StoreConverter.staffRepository = staffRepository;
    }

    public static StoreDTO toDTO(Store store) {
        AddressDTO addressDTO = AddressConverter.toDTO(store.getAddress());
        StaffDTO staffDTO = StaffConverter.toDTO(store.getManagerStaff());

        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setStoreId(store.getStoreId());
        storeDTO.setManagerStaff(staffDTO);
        storeDTO.setAddress(addressDTO);
        storeDTO.setLastUpdate(store.getLastUpdate());

        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO) {
        Address address = AddressConverter.toEntity(storeDTO.getAddress());
        Staff staff = StaffConverter.toEntity(storeDTO.getManagerStaff());

//        Staff staff = storeDTO.getManagerStaff();

//        Staff staff = new Staff();
//        staff.setStaffId(storeDTO.getManagerStaff().getStaffId());
//        staff.setFirstName(storeDTO.getManagerStaff().getFirstName());
//        staff.setLastName(storeDTO.getManagerStaff().getLastName());
//        staff.setAddress(address);
//        staff.setEmail(storeDTO.getManagerStaff().getEmail());
//        staff.setStore(storeDTO.getStoreId());
//        staff.setActive(storeDTO.getManagerStaff().getActive());
//        staff.setUsername(storeDTO.getManagerStaff().getUsername());
//        staff.setPassword(storeDTO.getManagerStaff().getPassword());
//        staff.setLastUpdate(storeDTO.getManagerStaff().getLastUpdate());
//        staff.setPicture(storeDTO.getManagerStaff().getPicture());

        Store store = new Store();
        store.setStoreId(storeDTO.getStoreId());
        store.setManagerStaff(staff);
        store.setAddress(address);
        store.setLastUpdate(storeDTO.getLastUpdate());

        return store;

//        Optional<Staff> staffOptional = staffRepository.findById(storeDTO.getManagerStaffId());
//        if (staffOptional.isPresent()) {
//            Staff staff = staffOptional.get();
//
//            Store store = new Store();
//
//            store.setStoreId(storeDTO.getStoreId());
//            store.setManagerStaff(staff);
//            store.setAddress(address);
//            store.setLastUpdate(storeDTO.getLastUpdate());
//
//            return store;
//        }

//        Staff staff = StaffConverter.toEntity();

    }
}
