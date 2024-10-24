package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "planos",
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

    public Pricing(
        @NotBlank String name, 
        @NotBlank String description, 
        @NotBlank Double value,
        @NotBlank Integer reportsLimit, 
        @NotBlank Integer employeesLimit, 
        LocalDateTime createdAt, 
        boolean active
    ) {

        this.name = name;
        this.description = description;
        this.value = value;
        this.reportsLimit = reportsLimit;
        this.employeesLimit = employeesLimit;
        this.createdAt = createdAt;
        this.active = active;
    
    }

    public Pricing(
        UUID id, 
        @NotBlank String name, 
        @NotBlank String description, 
        @NotBlank Double value,
        @NotBlank Integer reportsLimit, 
        @NotBlank Integer employeesLimit, 
        LocalDateTime createdAt, 
        boolean active
    ) {

        this.pricingId = id;
        this.name = name;
        this.description = description;
        this.value = value;
        this.reportsLimit = reportsLimit;
        this.employeesLimit = employeesLimit;
        this.createdAt = createdAt;
        this.active = active;
    
    }

}
