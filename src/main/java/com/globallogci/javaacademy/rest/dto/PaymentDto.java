package com.globallogci.javaacademy.rest.dto;

import com.globallogci.javaacademy.rest.model.Currency;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentDto {

    private Long id;

    @NotNull
    private BigDecimal amount;

    private Currency currency;

    private String description;

    private String status;

    private Date validToDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getValidToDate() {
        return validToDate;
    }

    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }
}
