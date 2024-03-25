package com.example.sistlabsolos.dtos.institution;

import java.util.List;

import com.example.sistlabsolos.models.Institution;

public record GetInstitutionsDto (
    List<Institution> institutions,
    String errorMessage
) {
    
}
