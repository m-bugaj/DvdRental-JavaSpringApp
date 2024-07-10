package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.InventoryDTO;
import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Inventory;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.model.Staff;

public class RentalConverter {
    public static RentalDTO toDTO(Rental rental) {
        InventoryDTO inventoryDTO = InventoryConverter.toDTO(rental.getInventory());
        CustomerDTO customerDTO = CustomerConverter.toDTO(rental.getCustomer());
        StaffDTO staffDTO = StaffConverter.toDTO(rental.getStaff());
        RentalDTO rentalDTO = new RentalDTO();

        rentalDTO.setRentalId(rental.getRentalId());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setInventory(inventoryDTO);
        rentalDTO.setCustomer(customerDTO);
        rentalDTO.setReturnDate(rental.getReturnDate());
        rentalDTO.setStaff(staffDTO);
        rentalDTO.setLastUpdate(rental.getLastUpdate());

        return rentalDTO;
    }

    public static Rental toEntity(RentalDTO rentalDTO) {
        Inventory inventory = InventoryConverter.toEntity(rentalDTO.getInventory());
        Customer customer = CustomerConverter.toEntity(rentalDTO.getCustomer());
        Staff staff = StaffConverter.toEntity(rentalDTO.getStaff());
        Rental rental = new Rental();

        rental.setRentalId(rentalDTO.getRentalId());
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setReturnDate(rentalDTO.getReturnDate());
        rental.setStaff(staff);
        rental.setLastUpdate(rentalDTO.getLastUpdate());

        return rental;
    }
}
