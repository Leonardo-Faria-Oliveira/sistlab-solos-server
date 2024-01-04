package com.example.sistlabsolos.dtos.pricing;

import java.util.Optional;

import com.example.sistlabsolos.models.Pricing;

public record GetPricingByIdDto(
    Optional<Pricing> pricing,
    String errorMessage
) {
    
}
