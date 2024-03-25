package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.ScaleAbstract;
import com.example.sistlabsolos.models.Scale;
import com.example.sistlabsolos.repositories.ScaleRepository;

@Service
public class ScaleService extends ScaleAbstract  {

    @Autowired
    ScaleRepository scaleRepository;

    @Override
    public Scale create(Scale obj) throws SQLException {
        
        var scale = this.scaleRepository.findByPropertyName(obj.getPropertyName());
        if(scale.isEmpty()){
            
            return this.scaleRepository.save(obj);
        }

        return null;
        
    }

    @Override
    public List<Scale> list() {
        
        return this.scaleRepository.findByOrderByCreatedAtDesc();
        
    }

    @Override
    public Scale update(UUID id, Scale obj) {
        
        var scale = this.scaleRepository.findById(id);
        if (scale.isEmpty()) {
            return null;
        }

        scale.get().setHigher(obj.getHigher());
        scale.get().setHigh(obj.getHigh());
        scale.get().setMedium(obj.getMedium());
        scale.get().setLow(obj.getLow());
        scale.get().setLower(obj.getLower());

        return scale.get();
        
    }

    @Override
    public Optional<Scale> getScaleById(UUID scaleId) {
        
        return this.scaleRepository.findById(scaleId);
        
    }

    @Override
    public Optional<Scale> getScaleByPropertyName(String propertyName) {
        
        return this.scaleRepository.findByPropertyName(propertyName);
        
    }
    
}
