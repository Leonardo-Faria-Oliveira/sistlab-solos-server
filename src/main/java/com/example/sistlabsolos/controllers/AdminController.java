package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
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

import com.example.sistlabsolos.dtos.admin.CreateAdminDto;
import com.example.sistlabsolos.dtos.admin.LogInAdminDto;
import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.AdminService;
import com.example.sistlabsolos.services.InstitutionService;
import com.example.sistlabsolos.services.RoleService;
import com.example.sistlabsolos.utils.EncrypterDecrypter;

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

    @PostMapping()
      public ResponseEntity<Admin> createAdmin(@RequestBody @Valid CreateAdminDto createAdminDto) throws BadRequestException{
        Role role = this.roleService.getRoleByName(createAdminDto.roleName());

        if(role == null){
            throw new ResourceNotFoundException("Role não encontrada");
        }

        Institution institution = this.institutionService.getInstitutionByName(createAdminDto.institutionName());
   
        if(institution == null){
            throw new ResourceNotFoundException("Instituição não encontrada");
        }
        
        Admin res = this.adminService.create(
            createAdminDto.name(),
            createAdminDto.email(),
            EncrypterDecrypter.encrypt(createAdminDto.password()),
            createAdminDto.contact(),
            LocalDateTime.now(),
            true,
            role,
            institution
        
        );
        if(res == null){
            throw new BadRequestException("Administrador ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

    @GetMapping()
    public ResponseEntity<List<Admin>> getAdmins(){

        var admins = this.adminService.getAdmins();
        
        for (Admin admin : admins) {
            // System.out.println(admin.getInstitution().getName());
            admin.setPassword(null);
        }
      
        return ResponseEntity.status(HttpStatus.OK).body(
           admins
        );

    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Admin>> getAdminById(
        @PathVariable(value = "id") UUID id
    ) throws Exception{

        var admin = this.adminService.getAdminById(id);

        if(admin.isEmpty()){
            throw new ResourceNotFoundException("Instituição não encontrada");
        }
        else{
            admin.get().setPassword(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
          admin  
        );
        
    }

    // @GetMapping("/auth")
    // public ResponseEntity<Optional<Admin>> logIn(
    //     @RequestBody @Valid LogInAdminDto logInAdminDto
    // ) throws Exception{

    //     var adminAccess = this.adminService.getAdminByEmailAndPassword(
    //         logInAdminDto.email(), 
    //         EncrypterDecrypter.encrypt(logInAdminDto.password())
    //     );

    //     if(adminAccess == null){
    //         throw new ResourceNotFoundException("Administrador não existe");
    //     }

        
    // }
   
}
