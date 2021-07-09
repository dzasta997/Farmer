package com.globallogci.javaacademy.rest.controller;

import com.globallogci.javaacademy.rest.dto.PaymentDto;
import com.globallogci.javaacademy.rest.mapper.PaymentMapper;
import com.globallogci.javaacademy.rest.model.Payment;
import com.globallogci.javaacademy.rest.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Map;

@RestController
@RequestMapping("payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentMapper paymentMapper;

    public PaymentController(PaymentService paymentService, PaymentMapper paymentMapper) {
        this.paymentService = paymentService;
        this.paymentMapper = paymentMapper;
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        final Payment payment = paymentMapper.toEntity(paymentDto);
        final Payment createdPayment = paymentService.createPayment(payment);
        final PaymentDto createdPaymentDto = paymentMapper.toDto(createdPayment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPaymentDto);
    }

    @PutMapping
    public ResponseEntity<PaymentDto> updatePayment(@Valid @RequestBody PaymentDto paymentDto) {
        final Payment payment = paymentMapper.toEntity(paymentDto);
        final Payment updatedPayment = paymentService.updatePayment(payment);
        final PaymentDto createdPaymentDto = paymentMapper.toDto(updatedPayment);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(createdPaymentDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PaymentDto> patchPayment(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        final Payment payment = paymentService.getById(id).orElseThrow();
        final PaymentDto paymentDto = paymentMapper.toDto(payment);
        fields.forEach((fieldName, fieldValue) -> {
            final Field field = ReflectionUtils.findField(PaymentDto.class, fieldName);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, paymentDto, fieldValue);
            }
        });
        final Payment updatedPayment = paymentService.updatePayment(paymentMapper.toEntity(paymentDto));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(paymentMapper.toDto(updatedPayment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDto> patchPayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

}
