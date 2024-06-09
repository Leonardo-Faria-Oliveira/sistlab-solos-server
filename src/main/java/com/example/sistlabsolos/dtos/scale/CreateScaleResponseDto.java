package com.example.sistlabsolos.dtos.scale;

import com.example.sistlabsolos.models.Scale;

public record CreateScaleResponseDto(
    Scale scale,
    String errorMessage
) {}
