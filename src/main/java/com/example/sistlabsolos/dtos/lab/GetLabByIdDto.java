package com.example.sistlabsolos.dtos.lab;

import java.util.Optional;

import com.example.sistlabsolos.models.Lab;

public record GetLabByIdDto(
    Optional<Lab> lab,
    String errorMessage
) {}
