package com.example.sistlabsolos.dtos.admin;

import com.example.sistlabsolos.models.Admin;

public record CreateAdminResponseDto(
    Admin admin,
    String errorMessage
) {
    
}
