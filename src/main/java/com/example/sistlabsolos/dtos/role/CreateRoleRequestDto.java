package com.example.sistlabsolos.dtos.role;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleRequestDto(
    @NotBlank(message = "nome é necessario") String name
) {}

