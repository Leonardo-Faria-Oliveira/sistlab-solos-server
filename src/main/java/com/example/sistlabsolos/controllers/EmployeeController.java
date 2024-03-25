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

            if(role.getName().contains("echnical") && createEmployeeDto.crea() == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Crea é obrigatorio")
                );
                
            }

            Lab lab = this.labService.getLabByName(createEmployeeDto.labName());
    
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateEmployeeResponseDto(null, "Laboratorio não existe")
                );
                
            }
            
            Employee res = this.employeeService.create(new Employee(
                createEmployeeDto.name(),
                createEmployeeDto.email(),
                Encrypter.encrypt(createEmployeeDto.password()),
                createEmployeeDto.contact(),
                LocalDateTime.now(),
                false,
                createEmployeeDto.job(),
                role,
                lab
            ));

            
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

    @GetMapping("{labName}")
    public ResponseEntity<GetEmployeesDto> getEmployees(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByLab(lab);
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


    @GetMapping("{labName}/name/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameDesc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByNameDesc(lab);
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

    @GetMapping("{labName}/name/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameAsc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByNameAsc(lab);
        
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

    @GetMapping("{labName}/email/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailDesc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByEmailDesc(lab);
        
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

    @GetMapping("{labName}/email/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailAsc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByEmailAsc(lab);
        
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

    @GetMapping("{labName}/job/desc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobDesc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByJobDesc(lab);
        
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

    @GetMapping("{labName}/job/asc")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobAsc(
        @PathVariable(value = "labName") String labName
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByJobAsc(lab);
        
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

    @GetMapping("{labName}/search/name/{name}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByNameSearch(
        @PathVariable(value = "labName") String labName,
        @PathVariable(value = "name") String name
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByNameSearch(lab, name);
        
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

    
    @GetMapping("{labName}/search/email/{email}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByEmailSearch(
        @PathVariable(value = "labName") String labName,
        @PathVariable(value = "email") String email
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByNameSearch(lab, email);
        
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

    
    @GetMapping("{labName}/search/job/{job}")
    public ResponseEntity<GetEmployeesDto> getEmployeesByJobSearch(
        @PathVariable(value = "labName") String labName,
        @PathVariable(value = "job") String job
    ){

        
        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetEmployeesDto(null, "Laboratorio não encontrado")
                );
            }

            var employees = this.employeeService.getEmployeesByNameSearch(lab, job);
        
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

    
    @PatchMapping("/status/{id}")
    public ResponseEntity<CreateEmployeeResponseDto> firstAccessEmployeeUpdate(
        @PathVariable(value = "id") UUID id,    
    @RequestBody @Valid boolean status
    ){

        try {

            var employee = this.employeeService.setEmployeeStatus(id, status);
            if(employee == null){
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new CreateEmployeeResponseDto(null, "Funcionario não encontrado")  
                );

            }

            employee.setPassword(null);
            return ResponseEntity.status(HttpStatus.OK).body(
                new CreateEmployeeResponseDto(
                    employee, 
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
