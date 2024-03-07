package com.example.sistlabsolos.dtos.technicalResponsible;

import java.util.Optional;

import com.example.sistlabsolos.models.TechnicalResponsible;

public record GetTechnicalResponsibleByEmailDto(
    Optional<TechnicalResponsible> account,
    String errorMessage
) {}
