package com.example.dvdRental.services;

import com.example.dvdRental.api.model.InventoryDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.InventoryConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.repositories.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    @Override
    public List<InventoryDTO> findAllInventories() {
        return inventoryRepository
                .findAll()
                .stream()
                .map(InventoryConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<InventoryDTO> findInventoryById(Integer inventoryId) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventoryId);

        if (optionalInventory.isPresent()) return optionalInventory.map(InventoryConverter::toDTO);

        return Optional.empty();    }

    @Override
    public InventoryDTO createNewInventory(InventoryDTO inventoryDTO) {
        Inventory inventory = InventoryConverter.toEntity(inventoryDTO);
        Inventory savedInventory = inventoryRepository.save(inventory);

        return InventoryConverter.toDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Integer inventoryId, InventoryDTO inventoryDTO) {
        return null;
    }

    @Override
    public void deleteInventory(Integer inventoryId) {

    }
}
