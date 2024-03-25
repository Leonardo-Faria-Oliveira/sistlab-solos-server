package com.example.sistlabsolos.dtos.admin;

import java.util.Optional;

import com.example.sistlabsolos.models.Admin;

public record GetAdminByEmailDto(
    Optional<Admin> account,
    String errorMessage
) {}
