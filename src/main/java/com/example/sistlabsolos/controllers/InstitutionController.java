package com.example.sistlabsolos.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sistlabsolos.dtos.institution.CreateInstitutionDto;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.services.InstitutionService;
import com.example.sistlabsolos.utils.CreateCodeInstitution;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/institution")
public class InstitutionController {

    @Autowired
    InstitutionService institutionService;

    @PostMapping()
      public ResponseEntity<Institution> createInstitution(@RequestBody @Valid CreateInstitutionDto createInstitutionDto) throws BadRequestException{
        
        Institution res = this.institutionService.create(
            createInstitutionDto.name(),
            CreateCodeInstitution.createCode(createInstitutionDto.name()).toString()
        );
        if(res == null){
            throw new BadRequestException("Instituicao ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

    @GetMapping()
    public ResponseEntity<List<Institution>> getInstitutions(){

        return ResponseEntity.status(HttpStatus.OK).body(
            this.institutionService.getInstitutions()
        );

    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Institution>> getInstitutionById(
        @PathVariable(value = "id") UUID id
    ) throws Exception{

        var institution = this.institutionService.getInstitutionById(id);

        if(institution.isEmpty()){
            throw new ResourceNotFoundException("Instituição não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            institution
        );
        
    }
   
}
