package com.example.sistlabsolos.abstracts;

import java.util.UUID;

import com.example.sistlabsolos.interfaces.institution.ICreateInstitutionResponse;
import com.example.sistlabsolos.interfaces.institution.IGetInstitutionsResponse;
import com.example.sistlabsolos.models.Institution;

public abstract class InstitutionAbstract {
    public abstract ICreateInstitutionResponse create(Institution institution);
    public abstract IGetInstitutionsResponse getInstitutions();
    public abstract ICreateInstitutionResponse getInstitutionById(UUID institutionId);
}
