package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.ClientAbstract;
import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.ClientRepository;


@Service
public class ClientService extends ClientAbstract {
    
    @Autowired
    ClientRepository clientRepository;

    public Client create(
        String name,
        String city,
        String contact,
        LocalDateTime createdAt,
        Lab lab
    ){

        var Client = new Client(
            name,
            city,
            contact,
            createdAt,
            lab
        );

        var alreadyBeenInserted = this.clientRepository.findByName(name);

        if(alreadyBeenInserted == null){
            return this.clientRepository.save(Client);

        }
        return null;
        
    
    }

    @Override
    public List<Client> getClients() {

        return this.clientRepository.findAll();

    }

    @Override
    public Optional<Client> getClientById(UUID clientId){
        
        return this.clientRepository.findById(clientId);
     
    }

    @Override
    public Client getClientByName(String name) {
        
        return this.clientRepository.findByName(name);
        
    }

   

}
