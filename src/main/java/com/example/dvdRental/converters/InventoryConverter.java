package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.FilmDTO;
import com.example.dvdRental.api.model.InventoryDTO;
import com.example.dvdRental.api.model.StoreDTO;
import com.example.dvdRental.model.Film;
import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.model.Store;

public class InventoryConverter {
    public static InventoryDTO toDTO(Inventory inventory) {
        FilmDTO filmDTO = FilmConverter.toDTO(inventory.getFilm());
        StoreDTO storeDTO = StoreConverter.toDTO(inventory.getStore());
        InventoryDTO inventoryDTO = new InventoryDTO();

        inventoryDTO.setInventoryId(inventory.getInventoryId());
        inventoryDTO.setFilm(filmDTO);
        inventoryDTO.setStore(storeDTO);
        inventoryDTO.setLastUpdate(inventory.getLastUpdate());

        return inventoryDTO;
    }

    public static Inventory toEntity(InventoryDTO inventoryDTO) {
        Film film = FilmConverter.toEntity(inventoryDTO.getFilm());
        Store store = StoreConverter.toEntity(inventoryDTO.getStore());
        Inventory inventory = new Inventory();

        inventory.setInventoryId(inventoryDTO.getInventoryId());
        inventory.setFilm(film);
        inventory.setStore(store);
        inventory.setLastUpdate(inventoryDTO.getLastUpdate());

        return inventory;
    }
}
