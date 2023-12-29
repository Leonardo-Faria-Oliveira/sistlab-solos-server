package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Institution;

@Repository
public abstract class InstitutionAbstract {
    public abstract Institution create(String name, String code);
    public abstract List<Institution> getInstitutions();
    public abstract Optional<Institution> getInstitutionById(UUID institutionId) throws Exception;
}
