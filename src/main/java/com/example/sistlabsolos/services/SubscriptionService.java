package com.example.sistlabsolos.services;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.SubscriptionAbstract;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;
import com.example.sistlabsolos.repositories.LabRepository;
import com.example.sistlabsolos.repositories.SubscriptionRepository;


@Service
public class SubscriptionService extends SubscriptionAbstract {
    
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    LabRepository labRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Subscription create(Subscription subscription) throws SQLException{

        var labAlreadyHasSubscription = this.labRepository.findByName(
            subscription.getLab().getName()
        );

        if(labAlreadyHasSubscription.getSubscriptionList().isEmpty()){
            
            return this.subscriptionRepository.save(subscription);

        }
        
        return null; 
    
    }

    @Override
    public List<Subscription> list() {

        return this.subscriptionRepository.findAll();

    }

    @Override
    public Optional<Subscription> getSubscriptionById(UUID subscriptionId){
        
        return this.subscriptionRepository.findById(subscriptionId);
     
    }

    @Override
    public List<Subscription> getSubscriptionsByLabId(Optional<Lab> lab) {
       
        return this.subscriptionRepository.findSubscriptionsByLab(lab);

    }

    @Override
    public Subscription update(UUID id, Subscription obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }


   

}
