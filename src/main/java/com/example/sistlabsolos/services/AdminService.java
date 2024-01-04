package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.AdminAbstract;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.AdminRepository;


@Service
public class AdminService extends AdminAbstract {
    
    @Autowired
    AdminRepository adminRepository;

    public Admin create(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        Role role,
        Institution institution
    ){

        var admin = new Admin(
            name, 
            email,
            password,
            contact,
            createdAt,
            active,
            role,
            institution
        );

        var alreadyBeenInserted = this.adminRepository.findByEmail(email);
        if(alreadyBeenInserted == null){

            return this.adminRepository.save(admin);

        }
        
        return null;
        
    
    }

    @Override
    public List<Admin> getAdmins() {

        return this.adminRepository.findAll();

    }

    @Override
    public Optional<Admin> getAdminById(UUID AdminId){
        
        Optional<Admin> admin = this.adminRepository.findById(AdminId);
        
        return admin;
     
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        
        Optional<Admin> admin = this.adminRepository.findByEmail(email);

        return admin;
    }

   

}
