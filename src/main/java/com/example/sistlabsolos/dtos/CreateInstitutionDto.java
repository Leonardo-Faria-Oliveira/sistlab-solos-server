package com.example.sistlabsolos.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateInstitutionDto(
    @NotBlank String name
) {}

