package com.example.dvdRental.services;

import com.example.dvdRental.api.model.PaymentDTO;
import com.example.dvdRental.converters.AddressConverter;
import com.example.dvdRental.converters.PaymentConverter;
import com.example.dvdRental.model.Address;
import com.example.dvdRental.model.Payment;
import com.example.dvdRental.model.Payment;
import com.example.dvdRental.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public List<PaymentDTO> findAllPayments() {
        return paymentRepository
                .findAll()
                .stream()
                .map(PaymentConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentDTO> findPaymentById(Integer paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.isPresent()) return optionalPayment.map(PaymentConverter::toDTO);

        return Optional.empty();    }

    @Override
    public PaymentDTO createNewPayment(PaymentDTO paymentDTO) {
        Payment payment = PaymentConverter.toEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);

        return PaymentConverter.toDTO(savedPayment);
    }

    @Override
    public PaymentDTO updatePayment(Integer paymentId, PaymentDTO paymentDTO) {
        return null;
    }

    @Override
    public void deletePayment(Integer paymentId) {

    }
}
