package com.example.sistlabsolos.dtos.technicalResponsible;

import com.example.sistlabsolos.models.TechnicalResponsible;

public record CreateTechnicalResponsibleResponseDto(
    TechnicalResponsible technicalResponsible,
    String errorMessage
) {
} 