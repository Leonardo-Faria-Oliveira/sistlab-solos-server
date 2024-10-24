package com.example.sistlabsolos.seeders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sistlabsolos.enums.RolesEnum;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.repositories.AdminRepository;
import com.example.sistlabsolos.repositories.InstitutionRepository;
import com.example.sistlabsolos.repositories.RoleRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class AdminsSeeder implements ApplicationRunner{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("admin")) {
                seedAdmin();
            }
        }

    }

    public void seedAdmin(){

        var role = this.roleRepository.findByName(RolesEnum.ADMIN.getLabel());
        var institution = this.institutionRepository.findByName("UENP");

        this.adminRepository.save(new Admin(
            "José Reinaldo Merlin",
            "merlin@uenp.edu.br",
            "Soloview@123*",
            "18 99784-6302",
            LocalDateTime.of(2024, 8, 12, 14, 0, 0),
            true,
            role,
            institution
        )); 

    }
}
