package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "assinaturas")
public class Subscription{
    
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

    public Subscription(Integer usage, 
    @NotBlank Integer lateDays, 
    LocalDateTime createdAt, 
    boolean iPaid,
    boolean active, 
    Pricing pricing,
    Lab lab) {
        this.usage = usage;
        this.lateDays = lateDays;
        this.createdAt = createdAt;
        this.isPaid = iPaid;
        this.active = active;
        this.pricing = pricing;
        this.lab = lab;
    }

    public Subscription(UUID subscriptionId, 
    Integer usage, 
    @NotBlank Integer lateDays, 
    LocalDateTime createdAt,    
    boolean iPaid, 
    boolean active, 
    Pricing pricing,
    Lab lab) {
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

    public UUID getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(UUID subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getUsage() {
        return usage;
    }

    public void setUsage(Integer usage) {
        this.usage = usage;
    }

    public Integer getLateDays() {
        return lateDays;
    }

    public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean iPaid) {
        this.isPaid = iPaid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Pricing getPricing() {
        return pricing;
    }

    public void setPricing(Pricing pricing) {
        this.pricing = pricing;
    }
}
