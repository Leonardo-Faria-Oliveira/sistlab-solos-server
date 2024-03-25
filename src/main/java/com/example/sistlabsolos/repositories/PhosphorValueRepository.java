package com.example.sistlabsolos.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.PhosphorValue;

@Repository
public interface PhosphorValueRepository extends JpaRepository<PhosphorValue, UUID> {
    
}
