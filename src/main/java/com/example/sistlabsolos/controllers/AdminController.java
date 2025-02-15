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
import com.example.sistlabsolos.dtos.admin.CreateAdminRequestDto;
import com.example.sistlabsolos.dtos.admin.CreateAdminResponseDto;
import com.example.sistlabsolos.dtos.admin.GetAdminByEmailDto;
import com.example.sistlabsolos.dtos.admin.GetAdminByIdDto;
import com.example.sistlabsolos.dtos.admin.GetAdminsDto;
import com.example.sistlabsolos.dtos.auth.LogInRequestDto;
import com.example.sistlabsolos.dtos.auth.LogInResponseDto;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.AdminService;
import com.example.sistlabsolos.services.AuthService;
import com.example.sistlabsolos.services.InstitutionService;
import com.example.sistlabsolos.services.RoleService;
import com.example.sistlabsolos.utils.Encrypter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    RoleService roleService;

    @Autowired
    InstitutionService institutionService;

    @Autowired
    AuthService authService;
    
    @PostMapping()
      public ResponseEntity<CreateAdminResponseDto> createAdmin(@RequestBody @Valid CreateAdminRequestDto createAdminDto) throws BadRequestException{
        
        try {

            Institution institution = this.institutionService.getInstitutionByName(createAdminDto.institutionName());
    
            if(institution == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateAdminResponseDto(null, "Instituição não existe")
                );
                
            }
            
            Admin res = this.adminService.create(new Admin(
                    createAdminDto.name(),
                    createAdminDto.email(),
                    Encrypter.encrypt(createAdminDto.password()),
                    createAdminDto.contact(),
                    LocalDateTime.now(),
                    true,
                    new Role("admin"),
                    institution
                )
            );
            if(res == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateAdminResponseDto(null, "Administrador já existe")
                );
                
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateAdminResponseDto(res, null)
            );
            
        } catch (Exception e) {
                      
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateAdminResponseDto(null, e.getMessage())
            );

        }

        

    }

    @GetMapping()
    public ResponseEntity<GetAdminsDto> getAdmins(){

        try {

            var admins = this.adminService.list();
        
            for (Admin admin : admins) {

                admin.setPassword(null);
                
            
            }

        
            return ResponseEntity.ok(
                new GetAdminsDto(admins, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetAdminsDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAdminByIdDto> getAdminById(
        @PathVariable UUID id
    ){

        try {

            var admin = this.adminService.getAdminById(id);

            if(admin.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetAdminByIdDto(null, "Admnistrador não encontrado")  
                );

            }
            else{

                admin.get().setPassword(null);
            
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetAdminByIdDto(admin, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetAdminByIdDto(null, e.getMessage())  
            );


        }

        
        
    }

    @PostMapping("/auth/login")
    public ResponseEntity<LogInResponseDto> logIn(
        @RequestBody @Valid LogInRequestDto logInAdminDto
    ){

        try {
            var admin = this.adminService.getAdminByEmail(
                logInAdminDto.email()
            );
            
            if(admin.isEmpty()){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Administrador não existe")  
                );

            }

            var passwordVerify = BCrypt.verifyer().verify(logInAdminDto.password().toCharArray(), admin.get().getPassword());
            if(!passwordVerify.verified){
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Senha está incorreta")  
                );

            }

            var token = this.authService.generateToken(admin.get().getRole().getName());
            return ResponseEntity.status(HttpStatus.OK).body(
                new LogInResponseDto(token, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new LogInResponseDto(null, e.getMessage())  
            );
            
        } 
        
    }
   

    @GetMapping("email/{email}")
    public ResponseEntity<GetAdminByEmailDto> getAdminByEmail(
        @PathVariable String email
    ){

        try {

            var admin = this.adminService.getAdminByEmail(email);

            if(admin.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetAdminByEmailDto(null, "Admnistrador não encontrado")  
                );

            }

           
            admin.get().setPassword(null);
            admin.get().setInstitution(null);
            admin.get().setRole(null);

            admin.get().setRole(new Role("admin"));

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetAdminByEmailDto(admin, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetAdminByEmailDto(null, e.getMessage())  
            );


        }

        
        
    }

}
