package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.LabAbstract;
import com.example.sistlabsolos.models.Address;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.models.Subscription;
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

    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Lab create(
        String name,
        String email,
        String markUrl,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        Address address,
        Subscription subscription,
        Employee employee
    ) throws SQLException{

   

        var lab = new Lab(
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

            var newLab = this.labRepository.save(lab);

            subscription.setLab(newLab);
            this.subscriptionRepository.save(subscription);

            employee.setLab(newLab);
            this.employeeRepository.save(employee);

            return newLab;

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
