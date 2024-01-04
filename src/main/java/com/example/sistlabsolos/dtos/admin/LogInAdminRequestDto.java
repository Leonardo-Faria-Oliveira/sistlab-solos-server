package com.example.sistlabsolos.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record LogInAdminRequestDto (
    @NotBlank(message = "Email é obrigatorio") 
    @Email(message = "Deve ser um email")
    String email,

    @NotBlank(message = "Senha é obrigatoria") 
    @Min(value = 6, message = "Senha deve conter pelo menos 6 caracteres") 
    String password
) {
    
}
