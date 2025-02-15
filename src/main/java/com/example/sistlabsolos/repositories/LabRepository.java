package com.example.sistlabsolos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Lab;

@Repository
public interface LabRepository extends JpaRepository<Lab, UUID> {

    Lab findByName(String name);

}
