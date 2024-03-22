package com.example.sistlabsolos.dtos.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateEmployeeRequestDto(
    @NotBlank(message = "nome é necessario") 
    String name,

    @NotBlank(message = "email é necessario") 
    @Email
    String email, 

    @NotBlank(message = "senha é necessario") 
    String password, 
    
    @NotBlank(message = "tipo de conta é necessaria") 
    String roleName, 
    
    @NotBlank(message = "nome do laboratorio é necessario") 
    String labName,

    @NotBlank(message = "cargo do funcionário é necessario") 
    String job,

    String crea,
    
    String contact
) {
    
}
