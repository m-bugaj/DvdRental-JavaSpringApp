package com.example.dvdRental.services;

import com.example.dvdRental.api.model.InventoryDTO;

import java.util.List;
import java.util.Optional;

public interface InventoryService {
    List<InventoryDTO> findAllInventories();

    Optional<InventoryDTO> findInventoryById(Integer inventoryId);

    InventoryDTO createNewInventory(InventoryDTO inventoryDTO);

    InventoryDTO updateInventory(Integer inventoryId, InventoryDTO inventoryDTO);

    void deleteInventory(Integer inventoryId);
}
