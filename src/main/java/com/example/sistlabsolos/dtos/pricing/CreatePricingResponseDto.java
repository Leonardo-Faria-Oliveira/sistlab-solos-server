package com.example.sistlabsolos.dtos.pricing;

import com.example.sistlabsolos.models.Pricing;

public record CreatePricingResponseDto(
    Pricing pricing,
    String errorMessage
){}
