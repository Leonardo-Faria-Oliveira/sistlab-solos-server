package com.example.sistlabsolos.dtos.institution;

import jakarta.validation.constraints.NotBlank;

public record CreateInstitutionRequestDto(
    @NotBlank(message = "nome é necessario") String name
) {}

