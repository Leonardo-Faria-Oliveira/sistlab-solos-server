package com.example.sistlabsolos.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.InstitutionAbstract;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.repositories.InstitutionRepository;


@Service
public class InstitutionService extends InstitutionAbstract {
    
    @Autowired
    InstitutionRepository institutionRepository;

    public Institution create(String name, String code){
        
        System.out.println(name + " - "+ code);

        var institution = new Institution(
            name,
            code
        );

        var alreadyBeenInserted = this.institutionRepository.findByName(name);

        if(alreadyBeenInserted == null){
            return this.institutionRepository.save(institution);

        }
        return null;
        
    
    }

    @Override
    public List<Institution> getInstitutions() {

        return this.institutionRepository.findAll();

    }

    @Override
    public Optional<Institution> getInstitutionById(UUID institutionId){
        
        return this.institutionRepository.findById(institutionId);
     
    }

    @Override
    public Institution getInstitutionByName(String name) {
        
        return this.institutionRepository.findByName(name);
        
    }

   

}
