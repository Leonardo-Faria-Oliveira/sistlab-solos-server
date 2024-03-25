package com.example.sistlabsolos.dtos.lab;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateLabRequestDto (
    @NotNull(message = "nome é necessario") 
    String name,

    @NotNull(message = "email é necessario") 
    @Email
    String email, 
    
    @NotNull(message = "cidade é necessario") 
    String city, 

    @NotNull(message = "estado é necessario") 
    String state, 

    @NotNull(message = "pais é necessario") 
    String country, 

    @NotNull(message = "numero é necessario") 
    Integer number, 

    @NotNull(message = "Plano de serviço é necessário")
    String pricingName,

    @NotBlank(message = "nome do funcionário é necessario") 
    String employeeName,

    @NotBlank(message = "email do funcionário é necessario") 
    @Email
    String employeeEmail, 

    @NotBlank(message = "senha é necessario") 
    String password, 

    @NotBlank(message = "cargo do funcionário é necessario") 
    String employeeJob,
    
    Integer zipCode,

    String contact,

    String markUrl,

    String employeeContact

) {
    
}
