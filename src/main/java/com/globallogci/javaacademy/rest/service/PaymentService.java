package com.globallogci.javaacademy.rest.service;

import com.globallogci.javaacademy.rest.exception.UpdatingPaymentWithoutIdException;
import com.globallogci.javaacademy.rest.model.Payment;
import com.globallogci.javaacademy.rest.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Optional<Payment> getById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Payment payment) {
        if (payment.getId() == null) {
            throw new UpdatingPaymentWithoutIdException();
        }
        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}
