package com.globallogci.javaacademy.rest.mapper;

import com.globallogci.javaacademy.rest.dto.PaymentDto;
import com.globallogci.javaacademy.rest.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PaymentMapper {

    public Payment toEntity(PaymentDto dto) {
        final Payment payment = new Payment();
        payment.setStatus(dto.getStatus());
        payment.setId(dto.getId());
        payment.setDescription(dto.getDescription());
        payment.setAmount(dto.getAmount());
        return payment;
    }

    public PaymentDto toDto(Payment entity) {
        final PaymentDto dto = new PaymentDto();
        final String status = Optional.ofNullable(entity.getStatus())
                .map(String::toUpperCase)
                .orElse("UNKNOWN");
        dto.setStatus(status);
        dto.setId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setAmount(entity.getAmount());
        return dto;
    }
}
