package com.example.sistlabsolos.dtos.role;

import java.util.Optional;

import com.example.sistlabsolos.models.Role;


public record GetRoleByIdDto(
    Optional<Role> role,
    String errorMEssage
) {
    
}
