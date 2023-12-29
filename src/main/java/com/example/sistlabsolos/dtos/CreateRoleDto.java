package com.example.sistlabsolos.dtos;

import jakarta.validation.constraints.NotBlank;

public record CreateRoleDto(
    @NotBlank String name
) {}

