package com.example.sistlabsolos.dtos.pricing;

import java.util.List;

import com.example.sistlabsolos.models.Pricing;

public record GetPricingsDto(
    List<Pricing> pricings,
    String errorMessage
){
    
}
