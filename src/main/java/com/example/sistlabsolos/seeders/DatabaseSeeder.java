package com.example.sistlabsolos.seeders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.example.sistlabsolos.models.Admin;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class DatabaseSeeder implements ApplicationRunner{

    @Autowired
    private RolesSeeder rolesSeeder;

    @Autowired
    private InstitutionsSeeder institutionsSeeder;

    @Autowired
    private AdminsSeeder adminsSeeder;

    @Autowired
    private PricingsSeeder pricingsSeeder;

    @Autowired
    private LabsSeeder labsSeeder;

    @Autowired
    private ScalesSeeder scalesSeeder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(args.getOptionValues("seeder") != null){
            
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("all")) {

                this.rolesSeeder.seedRoles();
                this.institutionsSeeder.seedInstitution();
                this.adminsSeeder.seedAdmin();
                this.pricingsSeeder.seedPricings();
                this.labsSeeder.seedLab();
                this.scalesSeeder.seedScales();

            }
            
        }

    }
    
}
