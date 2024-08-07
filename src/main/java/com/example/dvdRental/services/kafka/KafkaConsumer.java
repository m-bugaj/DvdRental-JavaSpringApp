package com.example.dvdRental.services.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "customer-reports", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Otrzymano raport: " + message);
        log.info(message); // Zapisanie raportu do logów
    }
}
