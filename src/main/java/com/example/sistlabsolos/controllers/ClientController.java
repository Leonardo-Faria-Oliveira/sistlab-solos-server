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
import com.example.sistlabsolos.dtos.client.CreateClientRequestDto;
import com.example.sistlabsolos.dtos.client.CreateClientResponseDto;
import com.example.sistlabsolos.dtos.client.GetClientByIdDto;
import com.example.sistlabsolos.dtos.client.GetClientsDto;
import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.services.ClientService;
import com.example.sistlabsolos.services.LabService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @Autowired
    LabService labService;

    @PostMapping()
      public ResponseEntity<CreateClientResponseDto> createClient(
        @RequestBody @Valid CreateClientRequestDto createClientDto){
        try {

            Lab lab = this.labService.getLabByName(createClientDto.labName());
    
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateClientResponseDto(null, "Laboratorio não existe")
                );
                
            }

            Client res = this.clientService.create(
            createClientDto.name(),
            createClientDto.email(),
            createClientDto.city(),
            createClientDto.contact(),
            LocalDateTime.now(),
            lab
            );

            
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateClientResponseDto(null, "Cliente já existe"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateClientResponseDto(res, null));

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateClientResponseDto(null, e.getMessage()));
        }
        
    }

    @GetMapping()
    public ResponseEntity<GetClientsDto> getClients(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClients(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("name/desc")
    public ResponseEntity<GetClientsDto> getClientsByNameDesc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByNameDesc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("name/asc")
    public ResponseEntity<GetClientsDto> getClientsByNameAsc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByNameAsc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("city/desc")
    public ResponseEntity<GetClientsDto> getClientsByCityDesc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByCityDesc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("city/asc")
    public ResponseEntity<GetClientsDto> getClientsByCityAsc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByCityAsc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("search/name/{name}")
    public ResponseEntity<GetClientsDto> getClientsByNameSearch(
        @PathVariable(value = "name") String name
    ){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByNameSearch(name), null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }

    }

    @GetMapping("search/city/{city}")
    public ResponseEntity<GetClientsDto> getClientsByCitySearch(
        @PathVariable(value = "city") String city
    ){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByCitySearch(city), null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }
           
    }

    @GetMapping("email/asc")
    public ResponseEntity<GetClientsDto> getClientsByEmailAsc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByEmailAsc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    
    @GetMapping("search/email/{email}")
    public ResponseEntity<GetClientsDto> getClientsByEmailSearch(
        @PathVariable(value = "email") String email
    ){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByEmailSearch(email), null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }
           
    }

    @GetMapping("email/desc")
    public ResponseEntity<GetClientsDto> getClientsByEmailDesc(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(this.clientService.getClientsByEmailDesc(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<GetClientByIdDto> getClientById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var Client = this.clientService.getClientById(id);

            if(Client.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientByIdDto(null, "Cliente não encontrado")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientByIdDto(Client, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientByIdDto(null, e.getMessage())
                );
            
        }


        
    }
   
}
