package com.example.sistlabsolos.dtos.client;

import java.util.List;

import com.example.sistlabsolos.models.Client;

public record GetClientsDto(
    List<Client> clients,
    String errorMessage
) {}
