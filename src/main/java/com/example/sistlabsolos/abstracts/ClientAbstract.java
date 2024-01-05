package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class ClientAbstract {
    public abstract Client create(
        String name,
        String city,
        String contact,
        LocalDateTime createdAt,
        Lab lab
    );
    public abstract List<Client> getClients();
    public abstract Optional<Client> getClientById(UUID clientId);
    public abstract Client getClientByName(String name);
}
