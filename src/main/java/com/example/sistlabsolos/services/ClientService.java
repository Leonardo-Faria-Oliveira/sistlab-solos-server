package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.ClientAbstract;
import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.ClientRepository;


@Service
public class ClientService extends ClientAbstract {
    
    @Autowired
    ClientRepository clientRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Client create(Client client) throws SQLException{

        var alreadyBeenInserted = this.clientRepository.findByEmail(client.getEmail());
        if(alreadyBeenInserted == null){
            return this.clientRepository.save(client);
        }

        return null;
        
    }

    @Override
    public List<Client> list() {

        return this.clientRepository.findByOrderByCreatedAtDesc();

    }
    
    @Override
    public List<Client> getClientByLab(Lab lab){
        
        return this.clientRepository.findByLabOrderByCreatedAtDesc(lab);
     
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
    public List<Client> getClientsByNameDesc(Lab lab) {
        
        return this.clientRepository.findByLabOrderByNameDesc(lab);
        
    }

    @Override
    public List<Client> getClientsByNameAsc(Lab lab) {
        
        return this.clientRepository.findByLabOrderByNameAsc(lab);
        
    }

    @Override
    public List<Client> getClientsByCityAsc(Lab lab) {

        return this.clientRepository.findByLabOrderByCityAsc(lab);
        
    }

    @Override
    public List<Client> getClientsByCityDesc(Lab lab) {

        return this.clientRepository.findByLabOrderByCityDesc(lab);
        
    }

    @Override
    public List<Client> getClientsByNameSearch(Lab lab, String name) {

        return this.clientRepository.findTop3ByLabAndNameContainingIgnoreCase(lab, name);
        
    }

    @Override
    public List<Client> getClientsByCitySearch(Lab lab, String city) {

        return this.clientRepository.findTop3ByLabAndCityContainingIgnoreCase(lab, city);
        
    }

    @Override
    public List<Client> getClientsByEmailAsc(Lab lab) {

        return this.clientRepository.findByLabOrderByEmailAsc(lab);
        
    }

    @Override
    public List<Client> getClientsByEmailDesc(Lab lab) {
  
        return this.clientRepository.findByLabOrderByEmailDesc(lab);
        
    }

    @Override
    public List<Client> getClientsByEmailSearch(Lab lab, String email) {
        return this.clientRepository.findTop3ByLabAndEmailContainingIgnoreCase(lab, email);
        
    }

    @Override
    public Client update(UUID id, Client obj) {
       
        var updatedClient = this.clientRepository.findById(id);

        if(updatedClient.isEmpty()){
            return null;
        }
        
        updatedClient.get().setName(obj.getName());
        updatedClient.get().setEmail(obj.getEmail());
        updatedClient.get().setContact(obj.getContact());
        updatedClient.get().setCity(obj.getCity());
    
        this.clientRepository.save(updatedClient.get());

        return updatedClient.get();
        
    }


   

}
