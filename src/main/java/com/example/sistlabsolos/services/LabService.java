package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.example.sistlabsolos.abstracts.LabAbstract;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.EmployeeRepository;
import com.example.sistlabsolos.repositories.LabRepository;
import com.example.sistlabsolos.repositories.SubscriptionRepository;


@Service
public class LabService extends LabAbstract {
    
    @Autowired
    LabRepository labRepository;

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Lab create(Lab lab) throws SQLException{

        var alreadyBeenInserted = this.labRepository.findByName(lab.getName());
        
        if(alreadyBeenInserted == null){

            var newLab = this.labRepository.save(lab);

            lab.getSubscriptionList().get(0).setLab(newLab);
            this.subscriptionRepository.save(lab.getSubscriptionList().get(0));

            lab.getEmployeeList().get(0).setLab(newLab);
            this.employeeRepository.save(lab.getEmployeeList().get(0));

            return newLab;

        }
        
        return null;
    
    }

    @Override
    public List<Lab> list() {

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

    @Override
    public Lab update(UUID id, Lab obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

   

}
