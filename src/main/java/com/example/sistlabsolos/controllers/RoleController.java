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
import com.example.sistlabsolos.dtos.CreateRoleDto;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.RoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping()
      public ResponseEntity<Role> createRole(@RequestBody @Valid CreateRoleDto createRoleDto) throws BadRequestException{
        Role res = this.roleService.create(
            createRoleDto.name()
        );
        if(res == null){
            throw new BadRequestException("Role ja existe");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(res);

    }

    @GetMapping()
    public ResponseEntity<List<Role>> getRoles(){

        return ResponseEntity.status(HttpStatus.OK).body(
            this.roleService.getRoles()
        );

    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Role>> getRoleById(
        @PathVariable(value = "id") UUID id
    ) throws Exception{

        var role = this.roleService.getRoleById(id);

        if(role.isEmpty()){
            throw new ResourceNotFoundException("Instituição não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
            role
        );
        
    }
   
}
