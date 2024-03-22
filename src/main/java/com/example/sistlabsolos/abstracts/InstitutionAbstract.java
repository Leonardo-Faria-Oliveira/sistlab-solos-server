package com.example.sistlabsolos.abstracts;


import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Institution;

@Repository
public abstract class InstitutionAbstract implements IDAO<Institution> {
    public abstract Optional<Institution> getInstitutionById(UUID institutionId);
    public abstract Institution getInstitutionByName(String name);
}
