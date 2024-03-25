package com.example.sistlabsolos.dtos.client;

import java.util.Optional;

import com.example.sistlabsolos.models.Client;

public record GetClientByIdDto(

    Optional<Client> client,
    String errorMessage

) {}
