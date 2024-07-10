package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/store")
public class StoreApiController {
    private final StoreService storeService;

    public StoreApiController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDTO> findAllStorees() {
        return storeService.findAllStores();
    }

    @GetMapping("/{id}")
    public Optional<StoreDTO> findStoreById(@PathVariable("id") Integer storeId) {
        return storeService.findStoreById(storeId);
    }

    @PostMapping
    public ResponseEntity<StoreDTO> createNewStore(@RequestBody StoreDTO storeDTO) {
        return new ResponseEntity<StoreDTO>(storeService.createNewStore(storeDTO), HttpStatus.CREATED);
    }
}
