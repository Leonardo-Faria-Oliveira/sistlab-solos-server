package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Scale;

@Repository
public interface ScaleRepository extends JpaRepository<Scale, UUID> {

    public List<Scale> findByOrderByCreatedAtDesc();
    Optional<Scale> findByPropertyName(String propertyName);
    
}
