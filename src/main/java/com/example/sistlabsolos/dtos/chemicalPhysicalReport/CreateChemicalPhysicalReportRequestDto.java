package com.example.sistlabsolos.dtos.chemicalPhysicalReport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateChemicalPhysicalReportRequestDto(
 
    @NotNull(message = "funcionario é necessario")
    String employeeEmail,

    @NotNull(message = "responsavel tecnico é necessario")
    String technicalResponsibleEmail,

    @NotBlank(message = "nome do laboratorio é necessario")
    String labName,

    @NotBlank(message = "nome da propriedade é necessario")
    String landName,

    @NotNull(message = "latitude da amostra coletada é necessaria")
    Double lat,

    @NotNull(message = "longitude da amostra coletada é necessaria")
    Double lng,

    @NotBlank(message = "cidade é necessario")
    String city,

    @NotBlank(message = "talhao é necessario")
    String field,

    @NotNull(message = "profundidade da medicao é necessario")
    Double depth,

    @NotNull(message = "ph é necessario")
    Double ph,

    @NotNull(message = "enxofre é necessario")
    Double sulfur,

    @NotNull(message = "absorbancia inicial é necessario")
    Double phosphorAbsorbance,

    @NotNull(message = "absorbancia calculada é necessario")
    Double phosphor,

    @NotNull(message = "areia é necessario")
    Double sand,

    @NotNull(message = "argila é necessario")
    Double clay,
    
    @NotNull(message = "silte é necessario")
    Double silt,

    @NotNull(message = "materia organica é necessario")
    Double organicMatter,

    @NotNull(message = "carbono é necessario")
    Double carbon,

    @NotNull(message = "carbono organico total é necessario")
    Double totalOrganicCarbon,

    @NotNull(message = "acidez potencial inicial é necessario")
    Double potencialAcidity,

    @NotNull(message = "potassio é necessario")
    Double potassium,

    @NotNull(message = "calcio é necessario")
    Double calcium,

    @NotNull(message = "magnesio é necessario")
    Double magnesium,

    @NotNull(message = "aluminio é necessario")
    Double aluminum,

    @NotNull(message = "sodio é necessario")
    Double sodium,

    @NotNull(message = "soma das bases é necessario")
    Double bases,

    @NotNull(message = "capacidade de troca de cations é necessario")
    Double ctc,

    @NotNull(message = "acidez potencial final é necessario")
    Double acidity,

    @NotNull(message = "saturacao do aluminio é necessario")
    Double aluminumPercent,

    @NotNull(message = "saturacao do calcio é necessario")
    Double calciumPercent,

    @NotNull(message = "saturacao do magnesio é necessario")
    Double magnesiumPercent,

    @NotNull(message = "saturacao do potassio é necessario")
    Double potassiumPercent,

    @NotNull(message = "saturacao da soma das bases é necessario")
    Double basesPercent,

    Double boron,

    Double manganese,

    Double zinc,

    Double copper,

    Double iron

) {}
