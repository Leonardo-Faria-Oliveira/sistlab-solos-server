package com.example.sistlabsolos.seeders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sistlabsolos.enums.PricingsEnum;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.repositories.PricingRepository;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class PricingsSeeder implements ApplicationRunner {
    
    @Autowired
    private PricingRepository pricingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("pricing")) {
                this.seedPricings();
            }
        }

    }

    public void seedPricings(){

        for (PricingsEnum pricing : PricingsEnum.values()) {

            this.pricingRepository.save(new Pricing(
                pricing.getName(),
                pricing.getDescription(),
                pricing.getValue(),
                pricing.getReportsLimit(),
                pricing.getEmployeesLimit(),
                LocalDateTime.now(),
                true
            ));
            
        }
         
    }

}
