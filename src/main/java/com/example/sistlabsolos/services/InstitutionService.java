package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.InstitutionAbstract;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.repositories.InstitutionRepository;


@Service
public class InstitutionService extends InstitutionAbstract {
    
    @Autowired
    InstitutionRepository institutionRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Institution create(Institution institution) throws SQLException{
        
        var alreadyBeenInserted = this.institutionRepository.findByName(institution.getName());
        if(alreadyBeenInserted == null){

            return this.institutionRepository.save(institution);

        }

        return null;
        
    }

    @Override
    public List<Institution> list() {

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

    @Override
    public Institution update(UUID id, Institution obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   

}
