package com.example.sistlabsolos.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Client findByName(String name);

}
