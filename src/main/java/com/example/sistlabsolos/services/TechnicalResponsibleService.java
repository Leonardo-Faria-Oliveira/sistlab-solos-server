package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.TechnicalResponsibleAbstract;
import com.example.sistlabsolos.models.TechnicalResponsible;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.TechnicalResponsibleRepository;


@Service
public class TechnicalResponsibleService extends TechnicalResponsibleAbstract {
    
    @Autowired
    TechnicalResponsibleRepository technicalResponsibleRepository;

    public TechnicalResponsible create(
        String name,
        String email, 
        String password, 
        String contact,
        LocalDateTime createdAt, 
        boolean active, 
        String job, 
        Role role, 
        Lab lab, 
        String crea
    ){

        var technicalResponsible = new TechnicalResponsible(
            name, 
            email,
            password,
            contact,
            createdAt,
            active,
            job,
            role,
            lab,
            crea
        );

        var alreadyBeenInserted = this.technicalResponsibleRepository.findByEmail(email);
        if(alreadyBeenInserted.isEmpty()){

            return this.technicalResponsibleRepository.save(technicalResponsible);

        }
        
        return null;
        
    
    }

    @Override
    public List<TechnicalResponsible> getTechnicalResponsibles() {

        return this.technicalResponsibleRepository.findAll();

    }

    @Override
    public Optional<TechnicalResponsible> getTechnicalResponsibleById(UUID TechnicalResponsibleId){
        
        return this.technicalResponsibleRepository.findById(TechnicalResponsibleId);
     
    }

    @Override
    public Optional<TechnicalResponsible> getTechnicalResponsibleByEmail(String email) {
        
        return this.technicalResponsibleRepository.findByEmail(email);

    }


   

}
