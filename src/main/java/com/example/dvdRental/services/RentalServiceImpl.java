package com.example.dvdRental.services;

import com.example.dvdRental.api.model.InventoryDTO;
import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.api.model.post.PostRentalDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.RentalConverter;
import com.example.dvdRental.exceptions.NotFoundException;
import com.example.dvdRental.model.*;
import com.example.dvdRental.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final InventoryRepository inventoryRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final FilmRepository filmRepository;

    public RentalServiceImpl(
            RentalRepository rentalRepository,
            InventoryRepository inventoryRepository,
            CustomerRepository customerRepository,
            StaffRepository staffRepository,
            FilmRepository filmRepository
    ) {
        this.rentalRepository = rentalRepository;
        this.inventoryRepository = inventoryRepository;
        this.customerRepository = customerRepository;
        this.staffRepository = staffRepository;
        this.filmRepository = filmRepository;
    }


    @Override
    public List<RentalDTO> findAllRentals() {
        return rentalRepository
                .findAll()
                .stream()
                .map(RentalConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RentalDTO> findRentalById(Integer rentalId) {
        Optional<Rental> optionalRental = rentalRepository.findById(rentalId);

        if (optionalRental.isPresent()) return optionalRental.map(RentalConverter::toDTO);

        return Optional.empty();
    }

    @Override
    public RentalDTO createNewRental(PostRentalDTO postRentalDTO) throws NotFoundException {

        List<Inventory> inventories = inventoryRepository.findInventoriesByFilmTitleAndStoreId(
                postRentalDTO.getFilmTitle(),
                postRentalDTO.getStoreId()
        );

        List<Rental> rentals = rentalRepository.findRentalsByInventoriesWhereReturnDateIsNotNull(inventories);
        if (rentals.isEmpty()) {
            throw new NotFoundException("Inventories where return date is not null");
        }
        Inventory availableInventory = rentals.getLast().getInventory();

        Optional<Film> filmOptional = filmRepository.findFirstByTitle(postRentalDTO.getFilmTitle());
        if (filmOptional.isEmpty()) {
            throw new NotFoundException("Film", "title", postRentalDTO.getFilmTitle());
        }
        Film film = filmOptional.get();

        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(postRentalDTO.getEmail());
        if (customerOptional.isEmpty()) {
            throw new NotFoundException("Customer", "email", postRentalDTO.getEmail());
        }
        Customer customer = customerOptional.get();

        Optional<Staff> staffOptional = staffRepository.findByEmail(postRentalDTO.getStaffEmail());
        if (staffOptional.isEmpty()) {
            throw new NotFoundException("Staff", "email", postRentalDTO.getStaffEmail());
        }
        Staff staff = staffOptional.get();

        Rental rental = new Rental();
        rental.setInventory(availableInventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setRentalDate(Timestamp.valueOf(LocalDateTime.now()));
        Rental savedRental = rentalRepository.save(rental);

        customer.setActive(1);
        customerRepository.save(customer);

        return RentalConverter.toDTO(savedRental);
    }

    @Override
    public RentalDTO returnRental(Integer rentalId) throws NotFoundException {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);
        if (rentalOptional.isEmpty()) {
            throw new NotFoundException("Rental", "Id", rentalId.toString());
        }
        Rental rental = rentalOptional.get();
//        Rental rental = RentalConverter.toEntity(rentalDTO);
        rental.setReturnDate(Timestamp.valueOf(LocalDateTime.now()));
        rentalRepository.save(rental);

        Optional<Customer> customerOptional = customerRepository
                .findCustomerByCustomerId(rental.getCustomer().getCustomerId());

        if (customerOptional.isEmpty()) {
            throw new NotFoundException("Customer", "Id", rental.getCustomer().getCustomerId().toString());
        }
        Customer customer = customerOptional.get();
        customer.setActive(1);
        customerRepository.save(customer);

        return RentalConverter.toDTO(rental);
    }

    @Override
    public RentalDTO updateRental(Integer rentalId, RentalDTO rentalDTO) {
        return null;
    }

    @Override
    public void deleteRental(Integer rentalId) {

    }
}
