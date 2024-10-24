package com.example.sistlabsolos.seeders;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.repositories.InstitutionRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class InstitutionsSeeder implements ApplicationRunner{

    @Autowired
    private InstitutionRepository institutionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("institution")) {
                this.seedInstitution();
            }
        }

    }

    public void seedInstitution(){

        this.institutionRepository.save(new Institution(
            "UENP",
            "123456"
        )); 

    }

}
