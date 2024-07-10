package com.example.dvdRental.api.controllers;

import com.example.dvdRental.api.model.AddressDTO;
import com.example.dvdRental.api.model.PaymentDTO;
import com.example.dvdRental.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment")
public class PaymentApiController {
    private final PaymentService paymentService;

    public PaymentApiController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public List<PaymentDTO> findAllPaymentes() {
        return paymentService.findAllPayments();
    }

    @GetMapping("/{id}")
    public Optional<PaymentDTO> findPaymentById(@PathVariable("id") Integer paymentId) {
        return paymentService.findPaymentById(paymentId);
    }

    @PostMapping
    public ResponseEntity<PaymentDTO> createNewPayment(@RequestBody PaymentDTO paymentDTO) {
        return new ResponseEntity<PaymentDTO>(paymentService.createNewPayment(paymentDTO), HttpStatus.CREATED);
    }
}
