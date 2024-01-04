package com.example.sistlabsolos.controllers;

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
import com.example.sistlabsolos.dtos.institution.CreateInstitutionRequestDto;
import com.example.sistlabsolos.dtos.institution.CreateInstitutionResponseDto;
import com.example.sistlabsolos.dtos.institution.GetInstitutionByIdDto;
import com.example.sistlabsolos.dtos.institution.GetInstitutionsDto;
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
      public ResponseEntity<CreateInstitutionResponseDto> createInstitution(@RequestBody @Valid CreateInstitutionRequestDto createInstitutionDto){
        try {

            Institution res = this.institutionService.create(
            createInstitutionDto.name(),
            CreateCodeInstitution.createCode(createInstitutionDto.name()).toString()
            );
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateInstitutionResponseDto(null, "Instituição já existe"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateInstitutionResponseDto(res, null));

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateInstitutionResponseDto(null, e.getMessage()));
        }
        
    }

    @GetMapping()
    public ResponseEntity<GetInstitutionsDto> getInstitutions(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetInstitutionsDto(this.institutionService.getInstitutions(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetInstitutionsDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("{id}")
    public ResponseEntity<GetInstitutionByIdDto> getInstitutionById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var institution = this.institutionService.getInstitutionById(id);

            if(institution.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetInstitutionByIdDto(null, "Instituição não encontrada")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetInstitutionByIdDto(institution, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetInstitutionByIdDto(null, e.getMessage())
                );
            
        }


        
    }
   
}
