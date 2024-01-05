package com.example.sistlabsolos.controllers;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sistlabsolos.dtos.lab.CreateLabRequestDto;
import com.example.sistlabsolos.dtos.lab.CreateLabResponseDto;
import com.example.sistlabsolos.dtos.lab.GetLabByIdDto;
import com.example.sistlabsolos.dtos.lab.GetLabsDto;
import com.example.sistlabsolos.models.Address;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.PricingService;
import com.example.sistlabsolos.services.SubscriptionService;

import jakarta.validation.Valid;

@RestController
@EnableTransactionManagement
@RequestMapping("/v1/lab")
public class LabController {

    @Autowired
    LabService labService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PricingService pricingService;

    @PostMapping("/create")
      public ResponseEntity<CreateLabResponseDto> createLab(
        @RequestBody @Valid CreateLabRequestDto createLabDto) throws SQLException{
        
        try {

            var now = LocalDateTime.now();
            var pricing = this.pricingService.getPricingByName(createLabDto.pricingName());
            
            if(pricing == null){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CreateLabResponseDto(null, "Plano de seviço não encontrada")
                );

            }

            var ad = new Address(
                    createLabDto.city(),
                    createLabDto.state(),
                    createLabDto.country(),
                    createLabDto.number(),
                    createLabDto.zipCode(),
                    now
                );
            
                System.out.println(ad.getNumber());
            

            Lab res = this.labService.create(
                createLabDto.name(),
                createLabDto.email(),
                createLabDto.markUrl(),
                createLabDto.contact(),
                now,
                true,
                ad,
                new Subscription(
                    0, 
                    0,
                    now,
                    true,
                    true, 
                    pricing,
                    new Lab()
                )
            );
            if(res == null){

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateLabResponseDto(null,"Lab já existe"));
            
            }
       
      
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateLabResponseDto(
                    res,
                    null
                )
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateLabResponseDto(null, e.getMessage()));
        }
        
    }

    @GetMapping()
    public ResponseEntity<GetLabsDto> getLabs(){

        try {

            var labs = this.labService.getLabs();

            for (Lab lab : labs) {

                lab.setEmployeeList(null);

            }

            return ResponseEntity.status(HttpStatus.OK).body(
                 new GetLabsDto(labs, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetLabsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<GetLabByIdDto> getLabById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var lab = this.labService.getLabById(id);

            if(lab.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetLabByIdDto(null, "Laboratorio não encontrada")
                );
            }

            for (Employee employee : lab.get().getEmployeeList()){
                employee.setLab(null);
            } 

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetLabByIdDto(lab, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetLabByIdDto(null, e.getMessage())
                );
            
        }


        
    }
   
}
