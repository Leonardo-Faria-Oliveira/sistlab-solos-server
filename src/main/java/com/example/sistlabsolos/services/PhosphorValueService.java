package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.PhosphorValueAbstract;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.PhosphorValue;
import com.example.sistlabsolos.repositories.PhosphorValueRepository;

@Service
public class PhosphorValueService extends PhosphorValueAbstract {

    @Autowired
    PhosphorValueRepository phosphorValueRepository;

    @Override
    public PhosphorValue create(PhosphorValue obj) throws SQLException {
        
        return this.phosphorValueRepository.save(obj);
        
    }

    @Override
    public List<PhosphorValue> list() {
        
        return this.phosphorValueRepository.findAll();
        
    }

    @Override
    public PhosphorValue update(UUID id, PhosphorValue obj) {
        
        var phosphorValue = this.phosphorValueRepository.findById(id);

        phosphorValue.get().setX1(obj.getX1());
        phosphorValue.get().setX2(obj.getX2());
        phosphorValue.get().setX3(obj.getX3());
        phosphorValue.get().setX4(obj.getX4());
        phosphorValue.get().setX5(obj.getX5());

        this.phosphorValueRepository.save(phosphorValue.get());

        return phosphorValue.get();
        
    }

    @Override
    public PhosphorValue getLastPhosphorValue(Lab lab) {
        
        return this.phosphorValueRepository.findFirstByLabOrderByCreatedAtDesc(lab);
        
    }

    @Override
    public Double calculatePhosphorAbsorbance(PhosphorValue phosphorValue) {
        return 
        (phosphorValue.getX1() - (2- Math.log10(phosphorValue.getY1())))
        +
        (phosphorValue.getX2() - (2- Math.log10(phosphorValue.getY2())))
        +
        (phosphorValue.getX3() - (2- Math.log10(phosphorValue.getY3())))
        +
        (phosphorValue.getX4() - (2- Math.log10(phosphorValue.getY4())))
        +
        (phosphorValue.getX5() - (2- Math.log10(phosphorValue.getY5())))
        ;
    }
    
}
