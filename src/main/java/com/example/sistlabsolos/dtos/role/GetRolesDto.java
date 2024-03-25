package com.example.sistlabsolos.dtos.role;

import java.util.List;

import com.example.sistlabsolos.models.Role;

public record GetRolesDto(
    List<Role> roles,
    String errorMessage
) {
    
}
