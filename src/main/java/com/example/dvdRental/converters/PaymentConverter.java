package com.example.dvdRental.converters;

import com.example.dvdRental.api.model.CustomerDTO;
import com.example.dvdRental.api.model.PaymentDTO;
import com.example.dvdRental.api.model.RentalDTO;
import com.example.dvdRental.api.model.StaffDTO;
import com.example.dvdRental.model.Customer;
import com.example.dvdRental.model.Payment;
import com.example.dvdRental.model.Rental;
import com.example.dvdRental.model.Staff;

public class PaymentConverter {
    public static PaymentDTO toDTO(Payment payment) {
        CustomerDTO customerDTO = CustomerConverter.toDTO(payment.getCustomer());
        StaffDTO staffDTO = StaffConverter.toDTO(payment.getStaff());
        RentalDTO rentalDTO = RentalConverter.toDTO(payment.getRental());
        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setPaymentId(payment.getPaymentId());
        paymentDTO.setCustomer(customerDTO);
        paymentDTO.setStaff(staffDTO);
        paymentDTO.setRental(rentalDTO);
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setPaymentDate(payment.getPaymentDate());

        return paymentDTO;
    }

    public static Payment toEntity(PaymentDTO paymentDTO) {
        Customer customer = CustomerConverter.toEntity(paymentDTO.getCustomer());
        Staff staff = StaffConverter.toEntity(paymentDTO.getStaff());
        Rental rental = RentalConverter.toEntity(paymentDTO.getRental());
        Payment payment = new Payment();

        payment.setPaymentId(paymentDTO.getPaymentId());
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentDate(paymentDTO.getPaymentDate());

        return payment;
    }
}
