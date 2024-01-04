package com.example.sistlabsolos.dtos.subscription;

import java.util.List;

import com.example.sistlabsolos.models.Subscription;

public record GetSubscriptionsDto (
    List<Subscription> subscription,
    String errorMessage
) {}
