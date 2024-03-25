package com.example.sistlabsolos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Institution;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, UUID> {
    Institution findByName(String name);
}
