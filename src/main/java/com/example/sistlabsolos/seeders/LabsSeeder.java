package com.example.sistlabsolos.seeders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import com.example.sistlabsolos.enums.RolesEnum;
import com.example.sistlabsolos.models.Address;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;
import com.example.sistlabsolos.repositories.EmployeeRepository;
import com.example.sistlabsolos.repositories.LabRepository;
import com.example.sistlabsolos.repositories.PricingRepository;
import com.example.sistlabsolos.repositories.RoleRepository;
import com.example.sistlabsolos.repositories.SubscriptionRepository;
import com.example.sistlabsolos.utils.Encrypter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class LabsSeeder implements ApplicationRunner {

    @Autowired
    private LabRepository labRepository;

    @Autowired
    private PricingRepository pricingRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if(args.getOptionValues("seeder") != null){
            List<String> seeder = Arrays.asList(args.getOptionValues("seeder").get(0).split(","));
            if(seeder.contains("lab")) {
                seedLab();
            }
        }

    }

    public void seedLab(){

        var lab = new Lab();
        lab.setName("Laboratório de solos - UENP");
        lab.setEmail("labsolos@uenp.edu.br");
        lab.setContact(null);
        lab.setMarkUrl(null);
        lab.setActive(true);

        var pricing = this.pricingRepository.findByName("universidade");
        var subscription = new Subscription(
            0,
            0,
            LocalDateTime.now(),
            true,
            true,
            pricing,
            lab
        );
        List<Subscription> subscriptionList = new ArrayList<>();
        subscriptionList.add(subscription);

        lab.setSubscriptionList(subscriptionList);

        var role = this.roleRepository.findByName(RolesEnum.LAB_ADMIN.getLabel());

        var labAdminEmployee = new Employee(
            "Esmeralda",
            "esmeralda@uenp.edu.br",
            Encrypter.encrypt("Soloview@123*"),
            "",
            LocalDateTime.now(),
            true,
            "Funcionária do Laboratório",
            "",
            role,
            lab
        );

        var address = new Address(
            "Bandeirantes",
            "Paraná",
            "Brasil",
            0,
            86360000,
            LocalDateTime.now()
        );


        lab.setAddress(address);

        var createdLab = this.labRepository.save(lab);

        labAdminEmployee.setLab(createdLab);
        subscription.setLab(createdLab);

        this.subscriptionRepository.save(subscription);
        this.employeeRepository.save(labAdminEmployee);


    }

}
