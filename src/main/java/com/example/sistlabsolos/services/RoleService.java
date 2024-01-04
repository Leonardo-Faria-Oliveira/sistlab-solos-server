package com.example.sistlabsolos.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistlabsolos.abstracts.RoleAbstract;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.RoleRepository;


@Service
public class RoleService extends RoleAbstract {
    
    @Autowired
    RoleRepository roleRepository;

    public Role create(String name){

        var Role = new Role(
            name
        );

        var alreadyBeenInserted = this.roleRepository.findByName(name);

        if(alreadyBeenInserted == null){
            return this.roleRepository.save(Role);

        }
        return null;
        
    
    }

    @Override
    public List<Role> getRoles() {

        return this.roleRepository.findAll();

    }

    @Override
    public Optional<Role> getRoleById(UUID roleId){
        
        return this.roleRepository.findById(roleId);
     
    }

    @Override
    public Role getRoleByName(String name) {
        
        return this.roleRepository.findByName(name);
        
        
    }

   

}
