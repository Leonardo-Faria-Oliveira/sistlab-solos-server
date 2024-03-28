package com.example.sistlabsolos.dtos.chemicalPhysicalReport;

import com.example.sistlabsolos.models.ChemicalPhysicalReport;

public record CreateChemicalPhysicalReportResponseDto(
    ChemicalPhysicalReport report,
    String errorMessage
) {}
