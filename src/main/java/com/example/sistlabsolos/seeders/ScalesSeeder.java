package com.example.sistlabsolos.seeders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sistlabsolos.enums.ScalesEnum;
import com.example.sistlabsolos.models.Scale;
import com.example.sistlabsolos.repositories.ScaleRepository;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ScalesSeeder implements ApplicationRunner {

    @Autowired
    private ScaleRepository scaleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        
        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("scales")) {
                seedScales();
            }
        }

    }

    public void seedScales(){

        for (ScalesEnum scale : ScalesEnum.values()) {

            this.scaleRepository.save(new Scale(
                scale.getName(),
                scale.getLower(),
                scale.getLow(),
                scale.getMedium(),
                scale.getHigh(),
                scale.getHigher(),
                LocalDateTime.now()
            ));

        }

    }
    
}
