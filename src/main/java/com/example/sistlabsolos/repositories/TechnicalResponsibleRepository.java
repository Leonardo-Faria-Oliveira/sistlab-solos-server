package com.example.sistlabsolos.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.TechnicalResponsible;

@Repository
public interface TechnicalResponsibleRepository extends JpaRepository<TechnicalResponsible, UUID> {

    Optional<TechnicalResponsible> findByEmail(String email);

}
