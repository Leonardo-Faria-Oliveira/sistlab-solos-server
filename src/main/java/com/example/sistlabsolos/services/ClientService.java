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
        String email,
        String city,
        String contact,
        LocalDateTime createdAt,
        Lab lab
    ){

        var client = new Client(
            name,
            email,
            city,
            contact,
            createdAt,
            lab
        );

        var alreadyBeenInserted = this.clientRepository.findByEmail(email);

        if(alreadyBeenInserted == null){
            return this.clientRepository.save(client);
        }

        return null;
        
    
    }

    @Override
    public List<Client> getClients() {

        return this.clientRepository.findByOrderByCreatedAtDesc();

    }
    

    @Override
    public Optional<Client> getClientById(UUID clientId){
        
        return this.clientRepository.findById(clientId);
     
    }

    @Override
    public Client getClientByName(String name) {
        
        return this.clientRepository.findByName(name);
        
    }

    @Override
    public List<Client> getClientsByNameDesc() {
        
        return this.clientRepository.findByOrderByNameDesc();
        
    }

    @Override
    public List<Client> getClientsByNameAsc() {
        
        return this.clientRepository.findByOrderByNameAsc();
        
    }

    @Override
    public List<Client> getClientsByCityAsc() {

        return this.clientRepository.findByOrderByCityAsc();
        
    }

    @Override
    public List<Client> getClientsByCityDesc() {

        return this.clientRepository.findByOrderByCityDesc();
        
    }

    @Override
    public List<Client> getClientsByNameSearch(String name) {

        return this.clientRepository.findTop3ByNameContainingIgnoreCase(name);
        
    }

    @Override
    public List<Client> getClientsByCitySearch(String city) {

        return this.clientRepository.findTop3ByCityContainingIgnoreCase(city);
        
    }

    @Override
    public List<Client> getClientsByEmailAsc() {

        return this.clientRepository.findByOrderByEmailAsc();
        
    }

    @Override
    public List<Client> getClientsByEmailDesc() {
  
        return this.clientRepository.findByOrderByEmailDesc();
        
    }

    @Override
    public List<Client> getClientsByEmailSearch(String email) {
        return this.clientRepository.findTop3ByEmailContainingIgnoreCase(email);
        
    }

   

}
