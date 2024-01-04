package com.example.sistlabsolos.dtos.admin;

import java.util.List;

import com.example.sistlabsolos.models.Admin;

public record GetAdminsDto(
    List<Admin> admin,
    String errorMessage
) {}
