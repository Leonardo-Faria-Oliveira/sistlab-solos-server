package com.example.sistlabsolos.dtos.client;

import com.example.sistlabsolos.models.Client;

public record CreateClientResponseDto(
    Client client,
    String errorMessage
) {}
