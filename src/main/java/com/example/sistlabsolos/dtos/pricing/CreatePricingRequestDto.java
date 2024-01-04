package com.example.sistlabsolos.dtos.pricing;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePricingRequestDto(
    @NotBlank(message = "nome é necessario") 
    String name,

    @NotBlank(message = "descrição é necessaria") 
    String description,

    @NotNull(message = "valor é necessario") 
    @DecimalMin(value = "0.00", message = "valor deve ser maior que 0")
    @DecimalMax(value = "120.00", message = "valor deve ser menos que 120")
    Double value,

    @NotNull(message = "limite de laudos é necessario") 
    @Min(value = 1, message = "minimo limite de laudos é 1")
    Integer reportsLimit,

    @NotNull(message = "limite de funcionários é necessario") 
    @Min(value = 1, message = "minimo limite de funcionarios é 1")
    Integer employeesLimit
) {}
