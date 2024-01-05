package com.example.sistlabsolos.dtos.lab;

import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;

public record CreateLabResponseDto(
    Lab lab,
    String errorMessage
) {

}
