package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Address;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class LabAbstract {
    public abstract Lab create(
        String name,
        String email,
        String markUrl,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        Address address
    );
    public abstract List<Lab> getLabs();
    public abstract Optional<Lab> getLabById(UUID labId);
    public abstract Lab getLabByName(String name);
}
