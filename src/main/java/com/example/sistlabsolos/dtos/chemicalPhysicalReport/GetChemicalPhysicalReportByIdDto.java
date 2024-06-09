package com.example.sistlabsolos.dtos.chemicalPhysicalReport;

import java.util.Optional;

import com.example.sistlabsolos.models.ChemicalPhysicalReport;

public record GetChemicalPhysicalReportByIdDto(

    Optional<ChemicalPhysicalReport> report,
    String errorMessage

) {}
