package com.globallogci.javaacademy.rest.repository;

import com.globallogci.javaacademy.rest.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
