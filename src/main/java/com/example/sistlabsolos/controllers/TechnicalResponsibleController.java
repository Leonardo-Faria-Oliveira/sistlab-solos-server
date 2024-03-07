package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sistlabsolos.dtos.auth.LogInRequestDto;
import com.example.sistlabsolos.dtos.auth.LogInResponseDto;
import com.example.sistlabsolos.dtos.technicalResponsible.CreateTechnicalResponsibleRequestDto;
import com.example.sistlabsolos.dtos.technicalResponsible.CreateTechnicalResponsibleResponseDto;
import com.example.sistlabsolos.dtos.technicalResponsible.GetTechnicalResponsibleByEmailDto;
import com.example.sistlabsolos.dtos.technicalResponsible.GetTechnicalResponsibleByIdDto;
import com.example.sistlabsolos.dtos.technicalResponsible.GetTechnicalsResponsibleDto;
import com.example.sistlabsolos.models.TechnicalResponsible;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.TechnicalResponsibleService;
import com.example.sistlabsolos.services.AuthService;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.RoleService;
import com.example.sistlabsolos.utils.Encrypter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/technical")
public class TechnicalResponsibleController {

    @Autowired
    TechnicalResponsibleService technicalResponsibleService;

    @Autowired
    RoleService roleService;

    @Autowired
    LabService labService;

    @Autowired
    AuthService authService;
    
    @PostMapping()
      public ResponseEntity<CreateTechnicalResponsibleResponseDto> createTechnicalResponsible(
        @RequestBody @Valid CreateTechnicalResponsibleRequestDto createTechnicalResponsibleDto) throws BadRequestException{
        
        try {

            Role role = this.roleService.getRoleByName(createTechnicalResponsibleDto.roleName());

            if(role == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateTechnicalResponsibleResponseDto(null, "Role não existe")
                );
                
            }

            Lab lab = this.labService.getLabByName(createTechnicalResponsibleDto.labName());
    
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateTechnicalResponsibleResponseDto(null, "Laboratorio não existe")
                );
                
            }
            
            TechnicalResponsible res = this.technicalResponsibleService.create(
                createTechnicalResponsibleDto.name(),
                createTechnicalResponsibleDto.email(),
                Encrypter.encrypt(createTechnicalResponsibleDto.password()),
                createTechnicalResponsibleDto.contact(),
                LocalDateTime.now(),
                true,
                createTechnicalResponsibleDto.job(),
                role,
                lab,
                createTechnicalResponsibleDto.crea()
            );
            if(res == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateTechnicalResponsibleResponseDto(null, "Funcionário já existe")
                );
                
            }

            res.setLab(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateTechnicalResponsibleResponseDto(res, null)
            );
            
        } catch (Exception e) {
                      
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateTechnicalResponsibleResponseDto(null, e.getMessage())
            );

        }

        

    }

    @GetMapping()
    public ResponseEntity<GetTechnicalsResponsibleDto> getTechnicalsResponsible(){

        
        try {

            var technicalResponsibles = this.technicalResponsibleService.getTechnicalResponsibles();
        
            for (TechnicalResponsible TechnicalResponsible : technicalResponsibles) {

                TechnicalResponsible.setPassword(null);
                TechnicalResponsible.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetTechnicalsResponsibleDto(technicalResponsibles, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetTechnicalsResponsibleDto(null, e.getMessage())
            );
            
        }

        
    }


    @GetMapping("{id}")
    public ResponseEntity<GetTechnicalResponsibleByIdDto> getTechnicalResponsibleById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var technicalResponsible = this.technicalResponsibleService.getTechnicalResponsibleById(id);

            if(technicalResponsible.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetTechnicalResponsibleByIdDto(null, null, "Funcionario não encontrado")  
                );

            }

            var technicalResponsibleResponseDto = new GetTechnicalResponsibleByIdDto(
                technicalResponsible, 
                technicalResponsible.get().getLab().getLabId(),
                null
            ); 

            technicalResponsible.get().setPassword(null);
            technicalResponsible.get().setLab(null);
            
            

            return ResponseEntity.status(HttpStatus.OK).body(

                technicalResponsibleResponseDto  
            
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetTechnicalResponsibleByIdDto(null, null, e.getMessage())  
            );


        }

        
        
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LogInResponseDto> logIn(
        @RequestBody @Valid LogInRequestDto logInTechnicalResponsibleDto
    ){

        try {

            var technicalResponsible = this.technicalResponsibleService.getTechnicalResponsibleByEmail(
                logInTechnicalResponsibleDto.email()
            );
            
            if(technicalResponsible.isEmpty()){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Funcionario não existe")  
                );

            }

            var passwordVerify = BCrypt.verifyer().verify(logInTechnicalResponsibleDto.password().toCharArray(), technicalResponsible.get().getPassword());
            if(!passwordVerify.verified){
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Senha está incorreta")  
                );

            }

            var token = this.authService.generateToken(technicalResponsible.get().getRole().getName());
            return ResponseEntity.status(HttpStatus.OK).body(
                new LogInResponseDto(token, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new LogInResponseDto(null, e.getMessage())  
            );
            
        } 
        
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<GetTechnicalResponsibleByEmailDto> getTechnicalResponsibleByEmail(
        @PathVariable(value = "email") String email
    ){

        System.out.println(email);

        try {

            var technicalResponsible = this.technicalResponsibleService.getTechnicalResponsibleByEmail(email);
            if(technicalResponsible.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetTechnicalResponsibleByEmailDto(null, "Funcionario não encontrado")  
                );

            }

            technicalResponsible.get().setPassword(null);
            technicalResponsible.get().setLab(null);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetTechnicalResponsibleByEmailDto(technicalResponsible, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetTechnicalResponsibleByEmailDto(null, e.getMessage())  
            );


        }

        
        
    }
   
}
