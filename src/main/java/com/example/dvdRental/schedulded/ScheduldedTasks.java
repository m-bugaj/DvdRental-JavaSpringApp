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
import java.util.HashMap;
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
        Timestamp dateTwoMonthsAgo = Timestamp.valueOf(twoMonthsAgo);

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

    @Scheduled(cron = "*/5 * * * * *")
    public void generateReportSchedulded() {
        LocalDateTime oneWeekAgo = LocalDate.now().minusWeeks(1).atTime(0,0);
        Timestamp dateOneWeekAgo = Timestamp.valueOf(oneWeekAgo);

       List<Rental> rentals = rentalRepository.findActiveRentalsForAtLeastWeek(dateOneWeekAgo);
       HashMap<Integer, List<String>> reportHashMap = new HashMap<>();

       for (Rental rental : rentals) {
           Integer customerId = rental.getCustomer().getCustomerId();
//           System.out.println(customerId);
           List<String> films = reportHashMap.getOrDefault(customerId, new ArrayList<>());
           films.add(rental.getInventory().getFilm().getTitle());
           reportHashMap.put(customerId,films);
       }

       for (Integer customerId : reportHashMap.keySet()) {
           Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerId(customerId);

           if (customerOptional.isPresent()) {
               Customer customer = customerOptional.get();
               for (String filmTitle : reportHashMap.get(customerId)) {
                   System.out.printf("ID: %d%n %s %s%n %s%n Film title: %s%n%n",
                           customerId,
                           customer.getFirstName(),
                           customer.getLastName(),
                           customer.getEmail(),
                           filmTitle);
               }
           }
       }
    }
}
