package com.example.sistlabsolos.dtos.technicalResponsible;

import java.util.List;

import com.example.sistlabsolos.models.TechnicalResponsible;

public record GetTechnicalsResponsibleDto(
    List<TechnicalResponsible> technicalResponsible,
    String errorMessage
) {}
