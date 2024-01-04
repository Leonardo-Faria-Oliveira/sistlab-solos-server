package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.SubscriptionAbstract;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.models.Subscription;
import com.example.sistlabsolos.repositories.LabRepository;
import com.example.sistlabsolos.repositories.SubscriptionRepository;


@Service
public class SubscriptionService extends SubscriptionAbstract {
    
    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    LabRepository labRepository;

    public Subscription create(
        Integer usage,
        Integer lateDays,
        LocalDateTime createdAt,
        boolean isPaid,
        boolean active,
        Pricing pricing,
        Lab lab
    ){

        var subscription = new Subscription(
            usage,
            lateDays,
            createdAt,
            isPaid,
            active,
            pricing,
            lab
        );

        var labAlreadyHasSubscription = this.labRepository.findByName(lab.getName());

        if(labAlreadyHasSubscription.getSubscriptionList().isEmpty()){
            
            return this.subscriptionRepository.save(subscription);

        }
        
        return null;
        
    
    }

    @Override
    public List<Subscription> getSubscriptions() {

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


   

}
