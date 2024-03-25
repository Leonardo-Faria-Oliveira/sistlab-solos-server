package com.example.sistlabsolos.dtos.institution;


import com.example.sistlabsolos.models.Institution;

public record CreateInstitutionResponseDto(
    Institution institution,
    String errorMessage
) {
    
}
