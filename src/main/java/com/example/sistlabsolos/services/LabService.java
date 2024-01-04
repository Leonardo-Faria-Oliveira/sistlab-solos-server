package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistlabsolos.abstracts.LabAbstract;
import com.example.sistlabsolos.models.Address;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.LabRepository;


@Service
public class LabService extends LabAbstract {
    
    @Autowired
    LabRepository labRepository;

    public Lab create(
        String name,
        String email,
        String markUrl,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        Address address
    ){

        var Lab = new Lab(
            name,
            email,
            markUrl,
            contact,
            createdAt,
            active,
            address
        );

        var alreadyBeenInserted = this.labRepository.findByName(name);

        if(alreadyBeenInserted == null){
            return this.labRepository.save(Lab);

        }
        return null;
        
    
    }

    @Override
    public List<Lab> getLabs() {

        return this.labRepository.findAll();

    }

    @Override
    public Optional<Lab> getLabById(UUID LabId){
        
        return this.labRepository.findById(LabId);
     
    }

    @Override
    public Lab getLabByName(String name) {
        
        return this.labRepository.findByName(name);
        
    }

   

}
