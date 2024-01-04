package com.example.sistlabsolos.dtos.lab;

import java.util.List;

import com.example.sistlabsolos.models.Lab;

public record GetLabsDto (

    List<Lab> labs,
    String errorMessage

) {}
