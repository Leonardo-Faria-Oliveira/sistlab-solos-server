package com.example.sistlabsolos.abstracts;


import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Pricing;

@Repository
public abstract class PricingAbstract implements IDAO<Pricing> {
    public abstract Optional<Pricing> getPricingById(UUID pricingId);
    public abstract Pricing getPricingByName(String name);
}
