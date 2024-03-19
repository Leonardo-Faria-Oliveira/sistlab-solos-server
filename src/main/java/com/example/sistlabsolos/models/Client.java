package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Client implements Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;
    
    @NotBlank
    private String email;

    @NotBlank
    private String city;

    private String contact;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;


    public Client() {

        this.name = "";
        this.city = "";
        this.contact = "";
        this.email = "";
        this.createdAt = LocalDateTime.now();
        this.lab = new Lab();
        
    }

    public Client(UUID id, @NotBlank String name, @NotBlank String email, @NotBlank String city, String contact, LocalDateTime createdAt,
            Lab lab) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.contact = contact;
        this.createdAt = createdAt;
        this.lab = lab;
        this.email = email;
    }

 
    public Client(@NotBlank String name, @NotBlank String email, @NotBlank String city, String contact, LocalDateTime createdAt, Lab lab) {
        this.name = name;
        this.city = city;
        this.contact = contact;
        this.createdAt = createdAt;
        this.lab = lab;
        this.email = email;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
