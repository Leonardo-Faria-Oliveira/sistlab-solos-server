package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.models.Subscription;

@Repository
public abstract class SubscriptionAbstract {
    public abstract Subscription create(
        Integer usage,
        Integer lateDays,
        LocalDateTime createdAt,
        boolean isPaid,
        boolean active,
        Pricing pricing,
        Lab lab
    );
    public abstract List<Subscription> getSubscriptions();
    public abstract Optional<Subscription> getSubscriptionById(UUID subscriptionId);
    public abstract List<Subscription> getSubscriptionsByLabId(Optional<Lab> lab);
}
