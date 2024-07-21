package com.example.dvdRental.schedulded;

import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.repositories.CustomerRepository;
import com.example.dvdRental.repositories.RentalRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduldedTasks {
    RentalRepository rentalRepository;
    CustomerRepository customerRepository;

    public ScheduldedTasks(RentalRepository rentalRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
    }

    @Scheduled(cron = "* 48 8 * * *")
    public void changeCustomerToInactiveSchedulded() {
        List<Customer> customers = new ArrayList<>();

        LocalDateTime twoMonthsAgo = LocalDate.now().minusMonths(2).atTime(0, 0);
        java.sql.Timestamp dateTwoMonthsAgo = Timestamp.valueOf(twoMonthsAgo);

        List<Rental> rentalList = rentalRepository
                .findRentalsByActiveCustomersToInactive(dateTwoMonthsAgo);

        for (Rental rental : rentalList) {
            Optional<Customer> customerOptional = customerRepository
                    .findCustomerByCustomerId(rental.getCustomer().getCustomerId());
            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customer.setActive(0);
                customers.add(customer);
            }
        }
        customerRepository.saveAll(customers);
    }
}
