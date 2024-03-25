package com.example.sistlabsolos.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {
    Admin findByName(String name);
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByEmailAndPassword(String email, String password);
}
