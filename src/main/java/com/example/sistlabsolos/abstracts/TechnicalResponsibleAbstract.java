package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.TechnicalResponsible;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;


@Repository
public abstract class TechnicalResponsibleAbstract {
    public abstract TechnicalResponsible create(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        String job,
        Role role,
        Lab lab,
        String crea
    );
    public abstract List<TechnicalResponsible> getTechnicalResponsibles();
    public abstract Optional<TechnicalResponsible> getTechnicalResponsibleById(UUID TechnicalResponsibleId);
    public abstract Optional<TechnicalResponsible> getTechnicalResponsibleByEmail(String email);
}
