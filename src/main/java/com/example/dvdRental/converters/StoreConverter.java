package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Store;

public class StoreConverter {
    public static StoreDTO toDTO(Store store) {
        AddressDTO addressDTO = AddressConverter.toDTO(store.getAddress());
        StoreDTO storeDTO = new StoreDTO();

        storeDTO.setStoreId(store.getStoreId());
        storeDTO.setManagerStaffId(store.getManagerStaffId());
        storeDTO.setAddress(addressDTO);
        storeDTO.setLastUpdate(store.getLastUpdate());

        return storeDTO;
    }

    public static Store toEntity(StoreDTO storeDTO) {
        Address address = AddressConverter.toEntity(storeDTO.getAddress()); // Assuming AddressConverter.toEntity exists
        Store store = new Store();

        store.setStoreId(storeDTO.getStoreId());
        store.setManagerStaffId(storeDTO.getManagerStaffId());
        store.setAddress(address);
        store.setLastUpdate(storeDTO.getLastUpdate());

        return store;
    }
}
