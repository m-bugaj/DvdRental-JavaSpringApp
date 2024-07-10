package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.InventoryDTO;
import com.example.dvdRental.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
public class InventoryApiController {
    private final InventoryService inventoryService;

    public InventoryApiController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryDTO> findAllInventoryes() {
        return inventoryService.findAllInventories();
    }

    @GetMapping("/{id}")
    public Optional<InventoryDTO> findInventoryById(@PathVariable("id") Integer inventoryId) {
        return inventoryService.findInventoryById(inventoryId);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> createNewInventory(@RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<InventoryDTO>(inventoryService.createNewInventory(inventoryDTO), HttpStatus.CREATED);
    }
}
