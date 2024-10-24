package com.example.sistlabsolos.seeders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.example.sistlabsolos.enums.RolesEnum;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class RolesSeeder implements ApplicationRunner{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("role")) {
                seedRoles();
            }
        }

    }

    public void seedRoles(){

        for (RolesEnum role : RolesEnum.values()) {

            this.roleRepository.save(new Role(role.getLabel()));

        }

    }
    
}
