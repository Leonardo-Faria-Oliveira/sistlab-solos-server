package com.example.sistlabsolos.dtos.client;


import jakarta.validation.constraints.NotBlank;


public record CreateClientRequestDto(

    @NotBlank(message = "nome é necessario")
    String name,

    @NotBlank(message = "email é necessario")
    String email,

    @NotBlank(message = "cidade é necessario")
    String city,

    @NotBlank(message = "contato é necessario")
    String contact,

    @NotBlank(message = "nome do laboratorio é necessario") 
    String labName

) {}
