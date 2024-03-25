package com.example.sistlabsolos.dtos.scale;

import jakarta.validation.constraints.NotNull;

public record CreateScaleRequestDto(

    @NotNull(message = "nome da propriedade é necessario")
    String propertyName ,

    @NotNull(message = "escala mais baixa é necessario")
    Double lower ,

    @NotNull(message = "escala baixa é necessario")
    Double low,

    @NotNull(message = "escala media é necessario")
    Double medium,

    @NotNull(message = "escala alta é necessario")
    Double high,

    @NotNull(message = "escala mais alto é necessario") 
    Double higher

) {}
