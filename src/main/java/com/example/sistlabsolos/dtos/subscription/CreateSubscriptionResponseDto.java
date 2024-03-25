package com.example.sistlabsolos.dtos.subscription;

import com.example.sistlabsolos.models.Subscription;

public record CreateSubscriptionResponseDto (
    Subscription subscription,
    String errorMessage
){
    
}
