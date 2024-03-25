package com.example.sistlabsolos.services;


import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.AdminAbstract;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.repositories.AdminRepository;


@Service
public class AdminService extends AdminAbstract {
    
    @Autowired
    AdminRepository adminRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )
    public Admin create(Admin admin) throws SQLException{

        var alreadyBeenInserted = this.adminRepository.findByEmail(admin.getEmail());
        System.out.println(alreadyBeenInserted);
        if(alreadyBeenInserted.isEmpty()){

            return this.adminRepository.save(admin);

        }
        
        return null;
        
    
    }

    @Override
    public List<Admin> list() {

        return this.adminRepository.findAll();

    }

    @Override
    public Optional<Admin> getAdminById(UUID AdminId){
        
        return this.adminRepository.findById(AdminId);
     
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        
        return this.adminRepository.findByEmail(email);

    }

    @Override
    public Admin update(UUID id, Admin obj) {
        
        var updatedAdmin = this.adminRepository.findById(id);

        if(updatedAdmin.isEmpty()){
            return null;
        }
        
        updatedAdmin.get().setName(obj.getName());
        updatedAdmin.get().setEmail(obj.getEmail());
        updatedAdmin.get().setPassword(obj.getPassword());
        updatedAdmin.get().setContact(obj.getContact());
    
        this.adminRepository.save(updatedAdmin.get());

        return updatedAdmin.get();
        
    }

   

}
