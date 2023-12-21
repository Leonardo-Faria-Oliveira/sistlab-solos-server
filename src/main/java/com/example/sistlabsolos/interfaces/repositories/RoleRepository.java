package com.example.sistlabsolos.interfaces.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
}
