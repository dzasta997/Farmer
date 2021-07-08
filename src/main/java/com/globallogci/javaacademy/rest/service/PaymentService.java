package com.globallogci.javaacademy.rest.service;

import com.globallogci.javaacademy.rest.exception.EntityNotFoundException;
import com.globallogci.javaacademy.rest.exception.EntityToUpdateHasNoIdException;
import com.globallogci.javaacademy.rest.exception.NewEntityWithIdException;
import com.globallogci.javaacademy.rest.model.Payment;
import com.globallogci.javaacademy.rest.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment createPayment(final Payment payment) {
        if (payment.getId() != null) {
            throw new NewEntityWithIdException(payment.getId(), Payment.class);
        }
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(final Payment payment) {
        if (payment.getId() == null) {
            throw new EntityToUpdateHasNoIdException(Payment.class);
        }
        if (!paymentRepository.existsById(payment.getId())) {
            throw new EntityNotFoundException(payment.getId(), Payment.class);
        }
        return paymentRepository.save(payment);
    }

    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPayment(Long id) {
        return paymentRepository.findById(id);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

}
