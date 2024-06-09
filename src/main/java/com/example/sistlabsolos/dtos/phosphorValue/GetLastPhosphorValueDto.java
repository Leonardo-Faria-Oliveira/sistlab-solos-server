package com.example.sistlabsolos.dtos.phosphorValue;


import com.example.sistlabsolos.models.PhosphorValue;

public record GetLastPhosphorValueDto(

    PhosphorValue phosphorValue,
    String errorMessage

) {}
