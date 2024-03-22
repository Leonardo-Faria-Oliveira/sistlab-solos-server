package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.RoleAbstract;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.RoleRepository;


@Service
public class RoleService extends RoleAbstract {
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Role create(Role role) throws SQLException{

        var alreadyBeenInserted = this.roleRepository.findByName(role.getName());

        if(alreadyBeenInserted == null){
            return this.roleRepository.save(role);

        }

        return null;
        
    }

    @Override
    public List<Role> list() {

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

    @Override
    public Role update(UUID id, Role obj) {
       
        var updatedRole = this.roleRepository.findById(id);

        if(updatedRole.isEmpty()){
            return null;
        }
        
        updatedRole.get().setName(obj.getName());
    
        this.roleRepository.save(updatedRole.get());

        return updatedRole.get();
        
    }

   

}
