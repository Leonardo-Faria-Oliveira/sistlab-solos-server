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
import com.example.sistlabsolos.dtos.admin.GetAdminByIdDto;
import com.example.sistlabsolos.dtos.admin.GetAdminsDto;
import com.example.sistlabsolos.dtos.admin.LogInAdminRequestDto;
import com.example.sistlabsolos.dtos.admin.LogInAdminResponseDto;
import com.example.sistlabsolos.interfaces.account.IAccount;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.AdminService;
import com.example.sistlabsolos.services.AuthService;
import com.example.sistlabsolos.services.InstitutionService;
import com.example.sistlabsolos.services.RoleService;
import com.example.sistlabsolos.utils.Encrypter;

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
      public ResponseEntity<?> createAdmin(@RequestBody @Valid CreateAdminRequestDto createAdminDto) throws BadRequestException{
        
        try {

            Role role = this.roleService.getRoleByName(createAdminDto.roleName());

            if(role == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateAdminResponseDto(null, "Role não existe")
                );
                
            }

            Institution institution = this.institutionService.getInstitutionByName(createAdminDto.institutionName());
    
            if(institution == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateAdminResponseDto(null, "Instituição não existe")
                );
                
            }
            
            Admin res = this.adminService.create(
                createAdminDto.name(),
                createAdminDto.email(),
                Encrypter.encrypt(createAdminDto.password()),
                createAdminDto.contact(),
                LocalDateTime.now(),
                true,
                role,
                institution
            
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
    public ResponseEntity<?> getAdmins(){

        // System.out.println("new GetAdminsDto(admins, null)");
        try {

            var admins = this.adminService.getAdmins();
        
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
        @PathVariable(value = "id") UUID id
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
    public ResponseEntity<LogInAdminResponseDto> logIn(
        @RequestBody @Valid LogInAdminRequestDto logInAdminDto
    ){

        try {

            var admin = this.adminService.getAdminByEmail(
                logInAdminDto.email()
            );
            
            if(admin.isEmpty()){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInAdminResponseDto(null, "Administrador não existe")  
                );

            }

            if(admin.get().getPassword() != Encrypter.encrypt(logInAdminDto.password())){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInAdminResponseDto(null, "Senha está incorreta")  
                );

            }

            IAccount account = new Admin(
                admin.get().getId(),
                admin.get().getName(),
                admin.get().getEmail(),
                admin.get().getContact(),
                admin.get().getCreatedAt(),
                admin.get().isActive(),
                admin.get().getRole(),
                admin.get().getInstitution()
            );

            var token = this.authService.generateToken(account);
            return ResponseEntity.status(HttpStatus.OK).body(
                new LogInAdminResponseDto(token, null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new LogInAdminResponseDto(null, e.getMessage())  
            );
            
        } 
        


        
    }
   
}
