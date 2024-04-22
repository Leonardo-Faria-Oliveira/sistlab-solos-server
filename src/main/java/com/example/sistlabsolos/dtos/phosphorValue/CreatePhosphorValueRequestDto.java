package com.example.sistlabsolos.dtos.phosphorValue;

import jakarta.validation.constraints.NotNull;

public record CreatePhosphorValueRequestDto(

    @NotNull(message = "x1 é necessario")
    Double X1,

    @NotNull(message = "x2  é necessario")
    Double X2 ,

    @NotNull(message = "x3 é necessario")
    Double X3,

    @NotNull(message = "x4 é necessario")
    Double X4,

    @NotNull(message = "x5 é necessario")
    Double X5,

    @NotNull(message = "y1 é necessario")
    Double Y1,

    @NotNull(message = "y2 é necessario")
    Double Y2 ,

    @NotNull(message = "y3 é necessario")
    Double Y3,

    @NotNull(message = "y4 é necessario")
    Double Y4,

    @NotNull(message = "y5 é necessario")
    Double Y5,

    @NotNull(message = "Valor de absorbancia é necessário")
    Double absorbanceValue

) {}
