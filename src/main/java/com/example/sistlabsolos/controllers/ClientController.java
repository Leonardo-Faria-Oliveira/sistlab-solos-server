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
                    new CreateClientResponseDto(
                        null, "Laboratorio não existe"
                    )
                );
                
            }

            Client res = this.clientService.create(
                new Client(
                    createClientDto.name(),
                    createClientDto.email(),
                    createClientDto.city(),
                    createClientDto.contact(),
                    LocalDateTime.now(),
                    lab
                )
            );
            
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateClientResponseDto(
                        null, "Cliente já existe"
                    )
                );
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateClientResponseDto(res, null)
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateClientResponseDto(null, e.getMessage())
            );

        }
        
    }

    @GetMapping("{labName}")
    public ResponseEntity<GetClientsDto> getClients(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientByLab(lab);
            for(Client client : clientList){

                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("{labName}/name/desc")
    public ResponseEntity<GetClientsDto> getClientsByNameDesc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByNameDesc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("{labName}/name/asc")
    public ResponseEntity<GetClientsDto> getClientsByNameAsc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByNameAsc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("{labName}/city/desc")
    public ResponseEntity<GetClientsDto> getClientsByCityDesc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByCityDesc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("{labName}/city/asc")
    public ResponseEntity<GetClientsDto> getClientsByCityAsc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByCityAsc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("{labName}/search/name/{name}")
    public ResponseEntity<GetClientsDto> getClientsByNameSearch(
        @PathVariable String labName,
        @PathVariable String name
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByNameSearch(lab, name);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }

    }

    @GetMapping("{labName}/search/city/{city}")
    public ResponseEntity<GetClientsDto> getClientsByCitySearch(
        @PathVariable String labName,
        @PathVariable String city
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByCitySearch(lab, city);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }
           
    }

    @GetMapping("{labName}/email/asc")
    public ResponseEntity<GetClientsDto> getClientsByEmailAsc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            
            var clientList = this.clientService.getClientsByEmailAsc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    
    @GetMapping("{labName}/search/email/{email}")
    public ResponseEntity<GetClientsDto> getClientsByEmailSearch(
        @PathVariable String labName,
        @PathVariable String email
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByEmailSearch(lab, email);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetClientsDto(null, e.getMessage())
                );
            
        }
           
    }

    @GetMapping("{labName}/email/desc")
    public ResponseEntity<GetClientsDto> getClientsByEmailDesc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetClientsDto(null, "Laboratorio não encontrado")
                );
            }

            var clientList = this.clientService.getClientsByEmailDesc(lab);
            for(Client client : clientList){
                
                client.setChemicalPhysicalReportList(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetClientsDto(clientList, null)
            );

            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetClientsDto(null, e.getMessage()));
            
        }

    }

    @GetMapping("id/{id}")
    public ResponseEntity<GetClientByIdDto> getClientById(
        @PathVariable UUID id
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
