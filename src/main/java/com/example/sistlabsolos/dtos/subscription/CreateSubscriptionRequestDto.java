package com.example.sistlabsolos.dtos.subscription;

import jakarta.validation.constraints.NotBlank;

public record CreateSubscriptionRequestDto (
    @NotBlank(message = "Plano de serviço é necessário")
    String pricingName,

    @NotBlank(message = "Laboratorio dono da assinatura é necessário")
    String labName
){
    
}
