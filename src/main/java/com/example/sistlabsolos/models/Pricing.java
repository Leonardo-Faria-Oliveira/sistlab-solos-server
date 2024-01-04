package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "planos",
uniqueConstraints = @UniqueConstraint(columnNames={"value"}),
indexes = @Index(columnList = "name"))
public class Pricing implements Serializable{
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID pricingId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Double value;

    @NotNull
    private Integer reportsLimit;

    @NotNull
    private Integer employeesLimit;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Value("${some.key:true}")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pricing", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Subscription> subscriptionList = new ArrayList<>();

    public Pricing() {        
        this.name = "";
        this.description = "";
        this.value = 0.00;
        this.reportsLimit = 0;
        this.employeesLimit = 0;
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    public Pricing(@NotBlank String name, @NotBlank String description, @NotBlank @UniqueElements Double value,
            @NotBlank Integer reportsLimit, @NotBlank Integer employeesLimit, LocalDateTime createdAt, boolean active) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.reportsLimit = reportsLimit;
        this.employeesLimit = employeesLimit;
        this.createdAt = createdAt;
        this.active = active;
    }

    public Pricing(UUID id, @NotBlank String name, @NotBlank String description, @NotBlank @UniqueElements Double value,
            @NotBlank Integer reportsLimit, @NotBlank Integer employeesLimit, LocalDateTime createdAt, boolean active) {
        this.pricingId = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.reportsLimit = reportsLimit;
        this.employeesLimit = employeesLimit;
        this.createdAt = createdAt;
        this.active = active;
    }

    public UUID getId() {
        return pricingId;
    }

    public void setId(UUID id) {
        this.pricingId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getReportsLimit() {
        return reportsLimit;
    }

    public void setReportsLimit(Integer reportsLimit) {
        this.reportsLimit = reportsLimit;
    }

    public Integer getEmployeesLimit() {
        return employeesLimit;
    }

    public void setEmployeesLimit(Integer employeesLimit) {
        this.employeesLimit = employeesLimit;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


}
