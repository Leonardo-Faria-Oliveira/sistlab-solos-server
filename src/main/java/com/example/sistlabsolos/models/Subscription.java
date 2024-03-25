package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;
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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "assinaturas")
public class Subscription implements Serializable{
    
    @Serial 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID subscriptionId;

    @Value("${some.key}:0")
    private Integer usage;

    @Value("${some.key}:0")
    private Integer lateDays;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Value("${some.key:true}")
    private boolean isPaid;

    @Value("${some.key:true}")
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pricing.pricingId")
    private Pricing pricing;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;

    public Subscription() {

        this.usage = 0;
        this.lateDays = 0;
        this.createdAt = LocalDateTime.now();
        this.isPaid = true;
        this.active = true;
        this.pricing = new Pricing();
        this.lab = new Lab();
    
    }

    public Subscription(
        Integer usage, 
        @NotBlank Integer lateDays, 
        LocalDateTime createdAt, 
        boolean iPaid,
        boolean active, 
        Pricing pricing,
        Lab lab
    ) {

        this.usage = usage;
        this.lateDays = lateDays;
        this.createdAt = createdAt;
        this.isPaid = iPaid;
        this.active = active;
        this.pricing = pricing;
        this.lab = lab;
    
    }

    public Subscription(
        UUID subscriptionId, 
        Integer usage, 
        @NotBlank Integer lateDays, 
        LocalDateTime createdAt,    
        boolean iPaid, 
        boolean active, 
        Pricing pricing,
        Lab lab
    ) {

        this.subscriptionId = subscriptionId;
        this.usage = usage;
        this.lateDays = lateDays;
        this.createdAt = createdAt;
        this.isPaid = iPaid;
        this.active = active;
        this.pricing = pricing;
        this.lab = lab;
    
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
}
