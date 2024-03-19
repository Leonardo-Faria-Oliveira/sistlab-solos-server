package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Employee;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    public List<Client> findByOrderByCreatedAtDesc();
    Client findByName(String name);
    Client findByEmail(String email);

}
