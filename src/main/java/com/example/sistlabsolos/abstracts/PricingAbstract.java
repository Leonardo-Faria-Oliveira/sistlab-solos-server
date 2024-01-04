package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Pricing;

@Repository
public abstract class PricingAbstract {
    public abstract Pricing create(
        String name, 
        String description,
        Double value,
        Integer reportsLimit,
        Integer employeesLimit,
        LocalDateTime createdAt,
        boolean active
    );
    public abstract List<Pricing> getPricings();
    public abstract Optional<Pricing> getPricingById(UUID pricingId);
    public abstract Pricing getPricingByName(String name);
}
