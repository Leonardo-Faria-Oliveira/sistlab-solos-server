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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "enderecos")
public class Address implements Serializable {
   
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String country;

    @NotNull
    private Integer number;

    private Integer zipCode;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "lab.labId")
    private Lab lab;


    public Address(
        @NotNull String city, 
        @NotNull String state, 
        @NotNull String country, 
        @NotNull Integer number,
        Integer zipCode, 
        LocalDateTime createdAt
    ) {

        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
        this.zipCode = zipCode;
        this.createdAt = createdAt;
    
    }

    public Address(
        @NotNull String city, 
        @NotNull String state,
        @NotNull String country, 
        @NotNull Integer number
    ) {

        this.city = city;
        this.state = state;
        this.country = country;
        this.number = number;
    
    }

    public Address(
        UUID id, 
        @NotNull String city, 
        @NotNull String state, 
        @NotNull String country,
        @NotNull Integer number, 
        Integer zipCode, 
        LocalDateTime createdAt
    ) {

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

}
