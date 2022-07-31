package com.globallogic.rest.dto;

import javax.validation.constraints.NotBlank;

public class FarmerDto {

    private Long id;
    private String name;
    private String status;

    @NotBlank
    private String city;

    public Long getId() {
        return id;
    }

    public FarmerDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public FarmerDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public FarmerDto setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCity() {
        return city;
    }

    public FarmerDto setCity(String city) {
        this.city = city;
        return this;
    }
}
