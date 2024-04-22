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
import com.example.sistlabsolos.dtos.subscription.CreateSubscriptionRequestDto;
import com.example.sistlabsolos.dtos.subscription.CreateSubscriptionResponseDto;
import com.example.sistlabsolos.dtos.subscription.GetSubscriptionByIdDto;
import com.example.sistlabsolos.dtos.subscription.GetSubscriptionsDto;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Subscription;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.PricingService;
import com.example.sistlabsolos.services.SubscriptionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    PricingService pricingService;

    @Autowired
    LabService labService;

    @PostMapping()
      public ResponseEntity<CreateSubscriptionResponseDto> createSubscription(
        @RequestBody @Valid CreateSubscriptionRequestDto createSubscriptionDto){
        try {

            var pricing = this.pricingService.getPricingByName(createSubscriptionDto.pricingName());
            if(pricing == null){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CreateSubscriptionResponseDto(null, "Plano de seviço não encontrada")
                );

            }

            var lab = this.labService.getLabByName(createSubscriptionDto.labName());
            if(lab == null){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CreateSubscriptionResponseDto(null, "Laboratório não encontrado")
                );

            }

            Subscription res = this.subscriptionService.create(
                new Subscription(
                    0,
                    0,
                    LocalDateTime.now(),
                    true,
                    true,
                    pricing,
                    new Lab()
                )
            );

            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateSubscriptionResponseDto(
                        null, "Lab já possui assinatura"
                    )
                );
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateSubscriptionResponseDto(res, null)
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateSubscriptionResponseDto(null, e.getMessage())
            );

        }
        
    }

    @GetMapping()
    public ResponseEntity<GetSubscriptionsDto> getSubscriptions(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetSubscriptionsDto(this.subscriptionService.list(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetSubscriptionsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<GetSubscriptionByIdDto> getSubscriptionById(
        @PathVariable UUID id
    ){

        try {

            var subscription = this.subscriptionService.getSubscriptionById(id);

            if(subscription.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetSubscriptionByIdDto(null, "Assinatura não encontrada")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetSubscriptionByIdDto(subscription, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetSubscriptionByIdDto(null, e.getMessage())
                );
            
        }

    }

    @GetMapping("/lab/{labId}")
    public ResponseEntity<GetSubscriptionsDto> getSubscriptionsByLabId(
        @PathVariable UUID labId
    ){

        var lab = this.labService.getLabById(labId);

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetSubscriptionsDto(this.subscriptionService.getSubscriptionsByLabId(lab), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetSubscriptionsDto(null, e.getMessage()));
            
        }


    }
   
}
