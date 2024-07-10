package com.example.dvdRental.services;

import com.example.dvdRental.api.model.StoreDTO;

import java.util.List;
import java.util.Optional;

public interface StoreService {
    List<StoreDTO> findAllStores();

    Optional<StoreDTO> findStoreById(Integer storeId);

    StoreDTO createNewStore(StoreDTO storeDTO);

    StoreDTO updateStore(Integer storeId, StoreDTO storeDTO);

    void deleteStore(Integer storeId);
}
