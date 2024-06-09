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
import com.example.sistlabsolos.dtos.scale.CreateScaleRequestDto;
import com.example.sistlabsolos.dtos.scale.CreateScaleResponseDto;
import com.example.sistlabsolos.dtos.scale.GetScaleByIdDto;
import com.example.sistlabsolos.dtos.scale.GetScalesDto;
import com.example.sistlabsolos.models.Scale;
import com.example.sistlabsolos.services.ScaleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/scale")
public class ScaleController {

    @Autowired
    ScaleService scaleService;

    @PostMapping()
      public ResponseEntity<CreateScaleResponseDto> createScale(
        @RequestBody @Valid CreateScaleRequestDto createScaleDto){
        try {

            Scale res = this.scaleService.create(
                new Scale(
                    createScaleDto.propertyName(),
                    createScaleDto.higher(),
                    createScaleDto.high(),
                    createScaleDto.medium(),
                    createScaleDto.low(),
                    createScaleDto.lower(),
                    LocalDateTime.now()
                 
                )
            );
            
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateScaleResponseDto(
                        null, "Escala já existe"
                    )
                );
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateScaleResponseDto(res, null)
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateScaleResponseDto(null, e.getMessage())
            );

        }
        
    }

    @GetMapping("")
    public ResponseEntity<GetScalesDto> getScales(){

        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetScalesDto(this.scaleService.list(), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetScalesDto(null, e.getMessage()));
            
        }


    }

    @GetMapping("/propertyName/{propertyName}")
    public ResponseEntity<GetScaleByIdDto> getScaleByPropertyName(
        @PathVariable String propertyName
    ){

        try {

            var scale = this.scaleService.getScaleByPropertyName(propertyName);
            if(scale.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetScaleByIdDto(null, "Escala não encontrada")
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetScaleByIdDto(scale, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetScaleByIdDto(null, e.getMessage()
            ));
            
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<GetScaleByIdDto> getScaleById(
        @PathVariable UUID id
    ){

        try {

            var scale = this.scaleService.getScaleById(id);

            if(scale.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetScaleByIdDto(null, "Scalee não encontrado")
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetScaleByIdDto(scale, null)
            );
            
        } catch (Exception e) {
            
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new GetScaleByIdDto(null, e.getMessage())
                );
            
        }
   
    }
   
}
