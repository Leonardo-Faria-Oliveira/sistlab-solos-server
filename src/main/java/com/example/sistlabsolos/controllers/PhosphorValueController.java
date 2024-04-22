package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sistlabsolos.dtos.phosphorValue.CreatePhosphorValueRequestDto;
import com.example.sistlabsolos.dtos.phosphorValue.CreatePhosphorValueResponseDto;
import com.example.sistlabsolos.dtos.phosphorValue.GetLastPhosphorValueDto;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.PhosphorValue;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.PhosphorValueService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/phosphorValue")
public class PhosphorValueController {

    @Autowired
    PhosphorValueService phosphorValueService;

    @Autowired
    LabService labService;

    @PostMapping("lab/{labName}/create")
      public ResponseEntity<CreatePhosphorValueResponseDto> createPhosphorValue(
        @PathVariable String labName,
        @RequestBody @Valid CreatePhosphorValueRequestDto createPhosphorValueDto){
        try {

            Lab lab = this.labService.getLabByName(labName);
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreatePhosphorValueResponseDto(
                        null, "Laboratorio não existe"
                    )
                );
                
            }

            var phosphorValue = new PhosphorValue(
                createPhosphorValueDto.X1(),
                createPhosphorValueDto.X2(),
                createPhosphorValueDto.X3(),
                createPhosphorValueDto.X4(),
                createPhosphorValueDto.X5(),
                createPhosphorValueDto.Y1(),
                createPhosphorValueDto.Y2(),
                createPhosphorValueDto.Y3(),
                createPhosphorValueDto.Y4(),
                createPhosphorValueDto.Y5(),
                LocalDateTime.now(),
                lab,
                createPhosphorValueDto.absorbanceValue()
            );

            var phosphorAbsorbance = this.phosphorValueService.calculatePhosphorAbsorbance(
                phosphorValue
            );

            phosphorValue.setAbsorbanceValue(phosphorAbsorbance);

            PhosphorValue res = this.phosphorValueService.create(
               phosphorValue
            );
            
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                    new CreatePhosphorValueResponseDto(
                        null, "Houve um erro interno"
                    )
                );
            }
            res.setChemicalPhysicalReportsList(null);
            res.setLab(null);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreatePhosphorValueResponseDto(res, null)
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreatePhosphorValueResponseDto(null, e.getMessage())
            );

        }
        
    }

    @GetMapping("lab/{labName}")
    public ResponseEntity<GetLastPhosphorValueDto> getLastPhosphorValue(
        @PathVariable String labName
    ){

        try 
        {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetLastPhosphorValueDto(null, "Laboratorio não encontrado")
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetLastPhosphorValueDto(this.phosphorValueService.getLastPhosphorValue(lab), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetLastPhosphorValueDto(null, e.getMessage())
            );
            
        }


    }

}
