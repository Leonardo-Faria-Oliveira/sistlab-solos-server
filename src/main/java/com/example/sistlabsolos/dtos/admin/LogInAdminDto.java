package com.example.sistlabsolos.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LogInAdminDto (
    @NotBlank 
    @Email
    String email,

    @NotBlank 
    String password
) {
    
}
