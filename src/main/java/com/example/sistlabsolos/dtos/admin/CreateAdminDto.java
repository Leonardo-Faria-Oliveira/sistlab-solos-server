package com.example.sistlabsolos.dtos.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateAdminDto(
    @NotBlank(message = "nome é necessario") 
    String name,

    @NotBlank(message = "email é necessario") 
    @Email
    String email, 

    @NotBlank(message = "senha é necessario") 
    String password, 
    
    @NotBlank(message = "tipo de conta é necessaria") 
    String roleName, 
    
    @NotBlank(message = "instituição é necessario") 
    String institutionName,
    
    String contact
    
) {}

