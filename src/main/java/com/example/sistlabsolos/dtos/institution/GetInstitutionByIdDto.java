package com.example.sistlabsolos.dtos.institution;

import java.util.Optional;

import com.example.sistlabsolos.models.Institution;

public record GetInstitutionByIdDto (
    Optional<Institution> institution,
    String errorMessage
) {
    
}
