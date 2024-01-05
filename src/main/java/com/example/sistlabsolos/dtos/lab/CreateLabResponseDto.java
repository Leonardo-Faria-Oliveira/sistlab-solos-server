package com.example.sistlabsolos.dtos.lab;

import com.example.sistlabsolos.models.Lab;

public record CreateLabResponseDto(
    Lab lab,
    String errorMessage
) {}
