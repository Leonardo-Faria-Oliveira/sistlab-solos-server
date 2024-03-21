package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
import java.util.UUID;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.sistlabsolos.dtos.auth.LogInRequestDto;
import com.example.sistlabsolos.dtos.auth.LogInResponseDto;
import com.example.sistlabsolos.dtos.employee.CreateEmployeeRequestDto;
import com.example.sistlabsolos.dtos.employee.CreateEmployeeResponseDto;
import com.example.sistlabsolos.dtos.employee.CreateTechnicalResponsibleRequestDto;
import com.example.sistlabsolos.dtos.employee.GetEmployeeByEmailDto;
import com.example.sistlabsolos.dtos.employee.GetEmployeeByIdDto;
import com.example.sistlabsolos.dtos.employee.GetEmployeesDto;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.services.EmployeeService;
import com.example.sistlabsolos.services.AuthService;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.RoleService;
import com.example.sistlabsolos.utils.Encrypter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RoleService roleService;

    @Autowired
    LabService labService;

    @Autowired
    AuthService authService;
    
    @PostMapping()
      public ResponseEntity<CreateEmployeeResponseDto> createEmployee(
        @RequestBody @Valid CreateEmployeeRequestDto createEmployeeDto) throws BadRequestException{
        
        try {

            Role role = this.roleService.getRoleByName(createEmployeeDto.roleName());

            if(role == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Role não existe")
                );
                
            }

            Lab lab = this.labService.getLabByName(createEmployeeDto.labName());
    
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Laboratorio não existe")
                );
                
            }
            
            Employee res = this.employeeService.create(
                createEmployeeDto.name(),
                createEmployeeDto.email(),
                Encrypter.encrypt(createEmployeeDto.password()),
                createEmployeeDto.contact(),
                LocalDateTime.now(),
                false,
                createEmployeeDto.job(),
                role,
                lab
            );
            if(res == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Funcionário já existe")
                );
                
            }

            res.setLab(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateEmployeeResponseDto(res, null)
            );
            
        } catch (Exception e) {
                      
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateEmployeeResponseDto(null, e.getMessage())
            );

        }

        

    }

    @PostMapping("technical")
      public ResponseEntity<CreateEmployeeResponseDto> createTechnicalResponsible(
        @RequestBody @Valid CreateTechnicalResponsibleRequestDto createTechnicalResponsibleDto) throws BadRequestException{
        
        try {

            Role role = this.roleService.getRoleByName(createTechnicalResponsibleDto.roleName());

            if(role == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Role não existe")
                );
                
            }

            Lab lab = this.labService.getLabByName(createTechnicalResponsibleDto.labName());
    
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Laboratorio não existe")
                );
                
            }
            
            Employee res = this.employeeService.createTechnicalResponsible(
                createTechnicalResponsibleDto.name(),
                createTechnicalResponsibleDto.email(),
                Encrypter.encrypt(createTechnicalResponsibleDto.password()),
                createTechnicalResponsibleDto.contact(),
                LocalDateTime.now(),
                false,
                createTechnicalResponsibleDto.job(),
                createTechnicalResponsibleDto.crea(),
                role,
                lab
                
            );
            if(res == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Funcionário já existe")
                );
                
            }

            res.setLab(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateEmployeeResponseDto(res, null)
            );
            
        } catch (Exception e) {
                      
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateEmployeeResponseDto(null, e.getMessage())
            );

        }

    
    }

    @GetMapping()
    public ResponseEntity<GetEmployeesDto> getEmployees(){

        
        try {

            var employees = this.employeeService.getEmployees();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }


    @GetMapping("name/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameDesc(){

        
        try {

            var employees = this.employeeService.getEmployeesByNameDesc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("name/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameAsc(){

        
        try {

            var employees = this.employeeService.getEmployeesByNameAsc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("email/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailDesc(){

        
        try {

            var employees = this.employeeService.getEmployeesByEmailDesc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("email/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailAsc(){

        
        try {

            var employees = this.employeeService.getEmployeesByEmailAsc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("job/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobDesc(){

        
        try {

            var employees = this.employeeService.getEmployeesByJobDesc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("job/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobAsc(){

        
        try {

            var employees = this.employeeService.getEmployeesByJobAsc();
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    @GetMapping("search/name/{name}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameSearch(
        @PathVariable(value = "name") String name
    ){

        
        try {

            var employees = this.employeeService.getEmployeesByNameSearch(name);
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    
    @GetMapping("search/email/{email}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailSearch(
        @PathVariable(value = "email") String email
    ){

        
        try {

            var employees = this.employeeService.getEmployeesByNameSearch(email);
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }

    
    @GetMapping("search/job/{job}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobSearch(
        @PathVariable(value = "job") String job
    ){

        
        try {

            var employees = this.employeeService.getEmployeesByNameSearch(job);
        
            for (Employee employee : employees) {

                employee.setPassword(null);
                employee.setLab(null);
            
            }

        
            return ResponseEntity.ok(
                new GetEmployeesDto(employees, null)
            );

        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GetEmployeesDto(null, e.getMessage())
            );
            
        }

        
    }


    @GetMapping("{id}")
    public ResponseEntity<GetEmployeeByIdDto> getEmployeeById(
        @PathVariable(value = "id") UUID id
    ){

        try {

            var employee = this.employeeService.getEmployeeById(id);

            if(employee.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeeByIdDto(null, null, "Funcionario não encontrado")  
                );

            }

            var employeeResponseDto = new GetEmployeeByIdDto(
                employee, 
                employee.get().getLab().getName(),
                null
            ); 

            employee.get().setPassword(null);
            employee.get().setLab(null);
            
            

            return ResponseEntity.status(HttpStatus.OK).body(

                employeeResponseDto  
            
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetEmployeeByIdDto(null, null, e.getMessage())  
            );


        }

    }

    @PostMapping("/auth/login")
    public ResponseEntity<LogInResponseDto> logIn(
        @RequestBody @Valid LogInRequestDto logInEmployeeDto
    ){

        try {

            var employee = this.employeeService.getEmployeeByEmail(
                logInEmployeeDto.email()
            );
            
            if(employee.isEmpty()){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Funcionario não existe")  
                );

            }

            if(!employee.get().isActive()){

                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Funcionario não existe")  
                );

            }

            var passwordVerify = BCrypt.verifyer().verify(logInEmployeeDto.password().toCharArray(), employee.get().getPassword());
            if(!passwordVerify.verified){
                
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new LogInResponseDto(null, "Senha está incorreta")  
                );

            }

            var token = this.authService.generateToken(employee.get().getRole().getName());
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
    public ResponseEntity<GetEmployeeByEmailDto> getEmployeeByEmail(
        @PathVariable(value = "email") String email
    ){


        try {

            var employee = this.employeeService.getEmployeeByEmail(email);
            if(employee.isEmpty()){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeeByEmailDto(null, null, "Funcionario não encontrado")  
                );

            }

            var labName = employee.get().getLab().getName();

            employee.get().setPassword(null);
            employee.get().setLab(null);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new GetEmployeeByEmailDto(
                    employee, 
                    labName,
                    null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetEmployeeByEmailDto(
                null,
                null,
                e.getMessage())  
            );


        }

    }

    @PatchMapping("/first-access/{email}")
    public ResponseEntity<CreateEmployeeResponseDto> firstAccessEmployeeUpdate(
        @PathVariable(value = "email") String email,
        @RequestBody @Valid String password
    ){

        try {

            var employee = this.employeeService.getEmployeeByEmail(email);
            if(employee == null){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CreateEmployeeResponseDto(null, "Funcionario não encontrado")  
                );

            }

            if(employee.get().isActive()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Funcionario já teve o primeiro acesso")  
                );
            }

            var newEmployee = this.employeeService.firstAccessEmployeeUpdate(employee.get(), Encrypter.encrypt(password));

            newEmployee.setPassword(null);
            newEmployee.setLab(null);
            
            return ResponseEntity.status(HttpStatus.OK).body(
                new CreateEmployeeResponseDto(
                    newEmployee, 
                    null)  
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateEmployeeResponseDto(
                null,
                e.getMessage())  
            );

        }

    }
   
}
