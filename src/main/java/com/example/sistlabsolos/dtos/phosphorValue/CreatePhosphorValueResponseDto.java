package com.example.sistlabsolos.dtos.phosphorValue;

import com.example.sistlabsolos.models.PhosphorValue;

public record CreatePhosphorValueResponseDto(
    PhosphorValue PhosphorValue,
    String errorMessage
) {}
