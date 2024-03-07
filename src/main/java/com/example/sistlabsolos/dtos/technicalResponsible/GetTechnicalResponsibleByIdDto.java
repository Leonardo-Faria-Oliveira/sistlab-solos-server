package com.example.sistlabsolos.dtos.technicalResponsible;

import java.util.Optional;
import java.util.UUID;
import com.example.sistlabsolos.models.TechnicalResponsible;

public record GetTechnicalResponsibleByIdDto(
    Optional<TechnicalResponsible> technicalResponsible,
    UUID labId,
    String errorMessage
) {}
