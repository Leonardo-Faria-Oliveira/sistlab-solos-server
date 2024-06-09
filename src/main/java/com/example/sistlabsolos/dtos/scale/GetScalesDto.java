package com.example.sistlabsolos.dtos.scale;

import java.util.List;

import com.example.sistlabsolos.models.Scale;

public record GetScalesDto(
    List<Scale> scales,
    String errorMessage
) {}
