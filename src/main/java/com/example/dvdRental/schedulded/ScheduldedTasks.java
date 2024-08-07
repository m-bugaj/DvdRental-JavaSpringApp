package com.example.dvdRental.schedulded;

import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.repositories.CustomerRepository;
import com.example.dvdRental.repositories.RentalRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.logging.FileHandler;

@Slf4j
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

//    @Scheduled(cron = "*/10 * * * * *")
//    @Scheduled(cron = "0 0 7 * * *")
//    @Scheduled(cron = "0 */1 * * * *")
//    @Scheduled(cron = "*/10 * * * * *")
    public void generateReportSchedulded() {
        LocalDateTime oneWeekAgo = LocalDate.now().minusWeeks(1).atTime(0,0);
        Timestamp dateOneWeekAgo = Timestamp.valueOf(oneWeekAgo);

       List<Rental> rentals = rentalRepository.findActiveRentalsForAtLeastWeek(dateOneWeekAgo);
       HashMap<Integer, List<String>> reportHashMap = new HashMap<>();

       for (Rental rental : rentals) {
           Integer customerId = rental.getCustomer().getCustomerId();
           List<String> films = reportHashMap.getOrDefault(customerId, new ArrayList<>());

           Period rentalPeriod = Period.between(
                   rental.getRentalDate().toLocalDateTime().toLocalDate(), LocalDate.now()
           );

           films.add(String.format(
                   "%s - Wypozyczono %d dni temu",
                   rental.getInventory().getFilm().getTitle(),
                   rentalPeriod.getDays())
           );
           reportHashMap.put(customerId,films);
       }

       for (Integer customerId : reportHashMap.keySet()) {
           Optional<Customer> customerOptional = customerRepository.findCustomerByCustomerId(customerId);

           if (customerOptional.isPresent()) {
               Customer customer = customerOptional.get();

               StringBuilder report = new StringBuilder();
               report.append("\nID: ").append(customerId).append("\n");
               report.append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
               report.append(customer.getEmail()).append("\n");
               report.append("Wypożyczone filmy:\n");

               for (String filmTitle : reportHashMap.get(customerId)) {
                   report.append("- ").append(filmTitle).append("\n");
               }
               log.info(report.toString()); // Zapisanie raportu do logów
           }
       }
    }
}
