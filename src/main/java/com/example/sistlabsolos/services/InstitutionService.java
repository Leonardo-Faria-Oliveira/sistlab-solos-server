package com.example.sistlabsolos.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.sistlabsolos.abstracts.InstitutionAbstract;
import com.example.sistlabsolos.interfaces.institution.ICreateInstitutionRequest;
import com.example.sistlabsolos.interfaces.institution.ICreateInstitutionResponse;
import com.example.sistlabsolos.interfaces.institution.IGetInstitutionsResponse;
import com.example.sistlabsolos.models.Institution;

public class InstitutionService{
    
    @Autowired
    InstitutionAbstract institutionAbstract;

    public ICreateInstitutionResponse create(ICreateInstitutionRequest req){

        var institution = new Institution(
            req.name,
            req.code
        );

        return this.institutionAbstract.create(institution);

    }

    public IGetInstitutionsResponse getInstitutions(){

        return this.institutionAbstract.getInstitutions();

    }

    public ICreateInstitutionResponse getInstitutionById(UUID institutionId){

        return this.institutionAbstract.getInstitutionById(institutionId);

    }

}
