package com.example.dvdRental.services;

import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.StoreConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Store;
import com.example.dvdRental.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    @Override
    public List<StoreDTO> findAllStores() {
        return storeRepository
                .findAll()
                .stream()
                .map(StoreConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StoreDTO> findStoreById(Integer storeId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);

        if (optionalStore.isPresent()) return optionalStore.map(StoreConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public StoreDTO createNewStore(StoreDTO storeDTO) {
        Store store = StoreConverter.toEntity(storeDTO);
        Store savedStore = storeRepository.save(store);

        return StoreConverter.toDTO(savedStore);
    }

    @Override
    public StoreDTO updateStore(Integer storeId, StoreDTO storeDTO) {
        return null;
    }

    @Override
    public void deleteStore(Integer storeId) {

    }
}
