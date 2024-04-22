package com.example.sistlabsolos.dtos.lab;


import com.example.sistlabsolos.models.Lab;

public record AddHeadersResponseDto(
    Lab lab,
    String errorMessage
) {}
