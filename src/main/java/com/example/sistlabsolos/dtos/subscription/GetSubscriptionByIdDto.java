package com.example.sistlabsolos.dtos.subscription;

import java.util.Optional;

import com.example.sistlabsolos.models.Subscription;

public record GetSubscriptionByIdDto(
    Optional<Subscription> subscription,
    String errorMessage
) {
    
}
