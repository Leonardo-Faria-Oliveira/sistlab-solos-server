package com.example.sistlabsolos.interfaces.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
}
