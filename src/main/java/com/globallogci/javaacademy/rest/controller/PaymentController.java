package com.globallogci.javaacademy.rest.controller;

import com.globallogci.javaacademy.rest.exception.EntityNotFoundException;
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
import java.util.List;
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

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id)
                .map(paymentMapper::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PaymentDto>> getPayments() {
        final List<Payment> payments = paymentService.getPayments();
        return ResponseEntity.ok(paymentMapper.convertToDtos(payments));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        final Payment payment = paymentMapper.convertToEntity(paymentDto);
        final Payment createdPayment = paymentService.createPayment(payment);
        return new ResponseEntity<>(paymentMapper.convertToDto(createdPayment), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PaymentDto> updatePayment(@RequestBody PaymentDto paymentDto) {
        final Payment payment = paymentMapper.convertToEntity(paymentDto);
        final Payment updatedPayment = paymentService.updatePayment(payment);
        return new ResponseEntity<>(paymentMapper.convertToDto(updatedPayment), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PaymentDto> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping( "/{id}")
    public void saveManager(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        Payment payment = paymentService.getPayment(id).orElseThrow(() -> new EntityNotFoundException(id, Payment.class));
        fields.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(Payment.class, fieldName);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, payment, fieldValue);
            }
        });
        paymentService.updatePayment(payment);
    }

}
