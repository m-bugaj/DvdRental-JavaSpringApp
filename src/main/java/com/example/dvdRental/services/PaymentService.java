package com.example.dvdRental.services;

import com.example.dvdRental.api.model.PaymentDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentDTO> findAllPayments();

    Optional<PaymentDTO> findPaymentById(Integer paymentId);

    PaymentDTO createNewPayment(PaymentDTO paymentDTO);

    PaymentDTO updatePayment(Integer paymentId, PaymentDTO paymentDTO);

    void deletePayment(Integer paymentId);
}
