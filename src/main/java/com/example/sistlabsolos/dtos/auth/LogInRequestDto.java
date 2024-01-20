package com.example.sistlabsolos.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LogInRequestDto (
    @NotBlank(message = "Email é obrigatorio") 
    @Email(message = "Deve ser um email")
    String email,

    @NotBlank(message = "Senha é obrigatoria") 
    String password
) {
    
}
