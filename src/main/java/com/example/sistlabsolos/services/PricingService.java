package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.PricingAbstract;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.repositories.PricingRepository;


@Service
public class PricingService extends PricingAbstract {
    
    @Autowired
    PricingRepository pricingRepository;

    public Pricing create(
        String name, 
        String description,
        Double value,
        Integer reportsLimit,
        Integer employeesLimit,
        LocalDateTime createdAt,
        boolean active
    ){

        var pricing = new Pricing(
            name, 
            description,
            value,
            reportsLimit,
            employeesLimit,
            createdAt,
            active
        );

        var alreadyBeenInserted = this.pricingRepository.findByName(name);

        if(alreadyBeenInserted == null){
            return this.pricingRepository.save(pricing);

        }
        return null;
        
    
    }

    @Override
    public List<Pricing> getPricings() {

        return this.pricingRepository.findAll();

    }

    @Override
    public Optional<Pricing> getPricingById(UUID PricingId){
        
        return this.pricingRepository.findById(PricingId);
     
    }

    @Override
    public Pricing getPricingByName(String name) {
        
        return this.pricingRepository.findByName(name);
        
    }

   

}
