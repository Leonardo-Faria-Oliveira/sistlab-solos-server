package com.example.sistlabsolos.services;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.PricingAbstract;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.repositories.PricingRepository;

@Service
public class PricingService extends PricingAbstract {
    
    @Autowired
    PricingRepository pricingRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Pricing create(Pricing pricing) throws SQLException{

        var alreadyBeenInserted = this.pricingRepository.findByName(pricing.getName());
        if(alreadyBeenInserted == null){
            return this.pricingRepository.save(pricing);

        }
        return null;
        
    }

    @Override
    public List<Pricing> list() {

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

    @Override
    public Pricing update(UUID id, Pricing obj) {
        
        var updatedPricing = this.pricingRepository.findById(id);

        if(updatedPricing.isEmpty()){
            return null;
        }
        
        updatedPricing.get().setName(obj.getName());
        updatedPricing.get().setDescription(obj.getDescription());
        updatedPricing.get().setReportsLimit(obj.getReportsLimit());
        updatedPricing.get().setValue(obj.getValue());
    
        this.pricingRepository.save(updatedPricing.get());

        return updatedPricing.get();
        
    }

}
