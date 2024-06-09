package com.example.sistlabsolos.dtos.scale;

import java.util.Optional;

import com.example.sistlabsolos.models.Scale;

public record GetScaleByIdDto(

    Optional<Scale> scale,
    String errorMessage

) {}
