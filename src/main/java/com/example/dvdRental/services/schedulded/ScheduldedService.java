package com.example.dvdRental.services.schedulded;

import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.repositories.CustomerRepository;
import com.example.dvdRental.repositories.RentalRepository;
import com.example.dvdRental.services.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ScheduldedService {

    RentalRepository rentalRepository;
    CustomerRepository customerRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    public ScheduldedService(RentalRepository rentalRepository, CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
    }

    @Scheduled(cron = "*/30 * * * * *")
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
//                log.info(report.toString()); // Zapisanie raportu do logów
                kafkaProducer.sendMessage(report.toString());
            }
        }
    }
}
