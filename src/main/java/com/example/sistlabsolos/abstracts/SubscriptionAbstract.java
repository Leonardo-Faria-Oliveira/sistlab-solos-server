package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;

@Repository
public abstract class SubscriptionAbstract implements IDAO<Subscription>{
    public abstract Optional<Subscription> getSubscriptionById(UUID subscriptionId);
    public abstract List<Subscription> getSubscriptionsByLabId(Optional<Lab> lab);
}
