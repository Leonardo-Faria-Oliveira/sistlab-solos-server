package com.example.sistlabsolos.controllers;

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
import com.example.sistlabsolos.dtos.role.CreateRoleRequestDto;
import com.example.sistlabsolos.dtos.role.CreateRoleResponseDto;
import com.example.sistlabsolos.dtos.role.GetRoleByIdDto;
import com.example.sistlabsolos.dtos.role.GetRolesDto;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.RoleService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping()
      public ResponseEntity<CreateRoleResponseDto> createRole(@RequestBody @Valid CreateRoleRequestDto createRoleDto) throws BadRequestException{
        
        try {
            
            Role res = this.roleService.create(
                new Role(
                    createRoleDto.name()
                )
            );

            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CreateRoleResponseDto(null, "Role já existe"));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateRoleResponseDto(res, null));


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateRoleResponseDto(null, e.getMessage()));
        }
        
    }

    @GetMapping()
    public ResponseEntity<GetRolesDto> getRoles(){

        
        try {

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetRolesDto(this.roleService.list(), null)
            );
            
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetRolesDto(null, e.getMessage())
            );

        }

    }

    @GetMapping("{id}")
    public ResponseEntity<GetRoleByIdDto> getRoleById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var role = this.roleService.getRoleById(id);

            if(role.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetRoleByIdDto(null, "Role não encontrada")
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetRoleByIdDto(role, null)
            );

            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetRoleByIdDto(null, e.getMessage())
            );
        }


        
    }
   
}
