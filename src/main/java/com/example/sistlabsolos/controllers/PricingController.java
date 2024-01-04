package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistlabsolos.dtos.pricing.CreatePricingRequestDto;
import com.example.sistlabsolos.dtos.pricing.CreatePricingResponseDto;
import com.example.sistlabsolos.dtos.pricing.GetPricingByIdDto;
import com.example.sistlabsolos.dtos.pricing.GetPricingsDto;
import com.example.sistlabsolos.models.Pricing;
import com.example.sistlabsolos.services.PricingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pricing")
public class PricingController {

    @Autowired
    PricingService pricingService;

    @PostMapping()
      public ResponseEntity<CreatePricingResponseDto> createPricing(@RequestBody @Valid CreatePricingRequestDto createPricingDto){
        try {

            Pricing res = this.pricingService.create(
            createPricingDto.name(),
            createPricingDto.description(),
            createPricingDto.value(),
            createPricingDto.reportsLimit(),
            createPricingDto.employeesLimit(),
            LocalDateTime.now(),
            true
            );
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreatePricingResponseDto(null, "Instituição já existe"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreatePricingResponseDto(res, null));

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreatePricingResponseDto(null, e.getMessage()));
        }
        
    }

    @GetMapping()
    public ResponseEntity<GetPricingsDto> getPricings(){

        try {
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetPricingsDto(this.pricingService.getPricings(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetPricingsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<GetPricingByIdDto> getPricingById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var Pricing = this.pricingService.getPricingById(id);

            if(Pricing.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetPricingByIdDto(null, "Instituição não encontrada")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetPricingByIdDto(Pricing, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetPricingByIdDto(null, e.getMessage())
                );
            
        }


        
    }
   
}
