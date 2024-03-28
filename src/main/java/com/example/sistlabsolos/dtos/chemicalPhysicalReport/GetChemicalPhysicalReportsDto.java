package com.example.sistlabsolos.dtos.chemicalPhysicalReport;

import java.util.List;

import com.example.sistlabsolos.models.ChemicalPhysicalReport;

public record GetChemicalPhysicalReportsDto(
    List<ChemicalPhysicalReport> reports,
    String errorMessage
) {}
