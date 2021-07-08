package com.globallogci.javaacademy.rest.mapper;

import com.globallogci.javaacademy.rest.dto.PaymentDto;
import com.globallogci.javaacademy.rest.model.Payment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PaymentMapper extends EntityDtoMapper<Payment, PaymentDto> {

    @Override
    public PaymentDto convertToDto(Payment entity) {
        final PaymentDto dto = new PaymentDto();
        dto.setAmount(entity.getAmount());
        dto.setCurrency(entity.getCurrency());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        dto.setStatus(StringUtils.capitalize(entity.getStatus()));
        dto.setValidToDate(entity.getValidToDate());
        return dto;
    }

    @Override
    public Payment convertToEntity(PaymentDto dto) {
        final Payment entity = new Payment();
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getId());
        entity.setStatus(dto.getStatus() == null ? null : dto.getStatus().toUpperCase());
        entity.setValidToDate(dto.getValidToDate());
        return entity;
    }

}
