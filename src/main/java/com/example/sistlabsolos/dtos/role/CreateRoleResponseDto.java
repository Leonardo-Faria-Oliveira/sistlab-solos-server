package com.example.sistlabsolos.dtos.role;

import com.example.sistlabsolos.models.Role;

public record CreateRoleResponseDto (
    Role role,
    String errorMessage
) {
    
}
