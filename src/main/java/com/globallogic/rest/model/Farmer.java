package com.globallogic.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private String city;

    @Column(updatable = false)
    private LocalDate createdDate = LocalDate.now();

    @Column(updatable = false)
    private String secretToken = UUID.randomUUID().toString();

    public Long getId() {
        return id;
    }

    public Farmer setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Farmer setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Farmer setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getSecretToken() {
        return secretToken;
    }

    public Farmer setSecretToken(String secretToken) {
        this.secretToken = secretToken;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Farmer setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Farmer setCity(String city) {
        this.city = city;
        return this;
    }
}
