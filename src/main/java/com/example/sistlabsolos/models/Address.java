package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "enderecos")
public class Address implements Serializable {
   
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private Integer number;

    private Integer zipCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "lab.labId")
    private Lab lab;


    public Address(@NotBlank String city, @NotBlank String state, @NotBlank String country, @NotBlank Integer number,
            Integer zipCode, LocalDateTime createdAt) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    }

    public Address(@NotBlank String city, @NotBlank String state, @NotBlank String country, @NotBlank Integer number) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
    }

    public Address(UUID id, @NotBlank String city, @NotBlank String state, @NotBlank String country,
            @NotBlank Integer number, Integer zipCode, LocalDateTime createdAt) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
