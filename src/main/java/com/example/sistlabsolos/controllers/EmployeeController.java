// package com.example.sistlabsolos.controllers;

// import java.time.LocalDateTime;
// import java.util.UUID;
// import org.apache.coyote.BadRequestException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.example.sistlabsolos.interfaces.account.IAccount;
// import com.example.sistlabsolos.models.Employee;
// import com.example.sistlabsolos.models.Institution;
// import com.example.sistlabsolos.models.Role;
// import com.example.sistlabsolos.services.EmployeeService;
// import com.example.sistlabsolos.services.AuthService;
// import com.example.sistlabsolos.services.InstitutionService;
// import com.example.sistlabsolos.services.LabService;
// import com.example.sistlabsolos.services.RoleService;
// import com.example.sistlabsolos.utils.Encrypter;

// import jakarta.validation.Valid;

// @RestController
// @RequestMapping("/v1/Employee")
// public class EmployeeController {

//     @Autowired
//     EmployeeService employeeService;

//     @Autowired
//     RoleService roleService;

//     @Autowired
//     LabService labService;

//     @Autowired
//     AuthService authService;
    
//     @PostMapping()
//       public ResponseEntity<?> createEmployee(@RequestBody @Valid CreateEmployeeRequestDto createEmployeeDto) throws BadRequestException{
        
//         try {

//             Role role = this.roleService.getRoleByName(createEmployeeDto.roleName());

//             if(role == null){
                
//                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                     new CreateEmployeeResponseDto(null, "Role não existe")
//                 );
                
//             }

//             Institution institution = this.institutionService.getInstitutionByName(createEmployeeDto.institutionName());
    
//             if(institution == null){
                
//                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                     new CreateEmployeeResponseDto(null, "Instituição não existe")
//                 );
                
//             }
            
//             Employee res = this.employeeService.create(
//                 createEmployeeDto.name(),
//                 createEmployeeDto.email(),
//                 Encrypter.encrypt(createEmployeeDto.password()),
//                 createEmployeeDto.contact(),
//                 LocalDateTime.now(),
//                 true,
//                 role,
//                 institution
            
//             );
//             if(res == null){
                
//                 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                     new CreateEmployeeResponseDto(null, "Employeeistrador já existe")
//                 );
                
//             }

//             return ResponseEntity.status(HttpStatus.CREATED).body(
//                 new CreateEmployeeResponseDto(res, null)
//             );
            
//         } catch (Exception e) {
                      
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                 new CreateEmployeeResponseDto(null, e.getMessage())
//             );

//         }

        

//     }

//     @GetMapping()
//     public ResponseEntity<?> getEmployees(){

//         // System.out.println("new GetEmployeesDto(Employees, null)");
//         try {

//             var Employees = this.EmployeeService.getEmployees();
        
//             for (Employee Employee : Employees) {

//                 Employee.setPassword(null);
            
//             }

        
//             return ResponseEntity.ok(
//                 new GetEmployeesDto(Employees, null)
//             );

//         } catch (Exception e) {
            
//             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                 new GetEmployeesDto(null, e.getMessage())
//             );
            
//         }

        
//     }

//     @GetMapping("{id}")
//     public ResponseEntity<GetEmployeeByIdDto> getEmployeeById(
//         @PathVariable(value = "id") UUID id
//     ){

//         try {

//             var Employee = this.EmployeeService.getEmployeeById(id);

//             if(Employee.isEmpty()){
                
//                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                     new GetEmployeeByIdDto(null, "Admnistrador não encontrado")  
//                 );

//             }
//             else{

//                 Employee.get().setPassword(null);
            
//             }

//             return ResponseEntity.status(HttpStatus.OK).body(
//                 new GetEmployeeByIdDto(Employee, null)  
//             );
            
//         } catch (Exception e) {
            
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                 new GetEmployeeByIdDto(null, e.getMessage())  
//             );


//         }

        
        
//     }

//     @PostMapping("/auth/login")
//     public ResponseEntity<LogInEmployeeResponseDto> logIn(
//         @RequestBody @Valid LogInEmployeeRequestDto logInEmployeeDto
//     ){

//         try {

//             var Employee = this.EmployeeService.getEmployeeByEmail(
//                 logInEmployeeDto.email()
//             );
            
//             if(Employee.isEmpty()){

//                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                     new LogInEmployeeResponseDto(null, "Employeeistrador não existe")  
//                 );

//             }

//             if(Employee.get().getPassword() != Encrypter.encrypt(logInEmployeeDto.password())){

//                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                     new LogInEmployeeResponseDto(null, "Senha está incorreta")  
//                 );

//             }

//             IAccount account = new Employee(
//                 Employee.get().getId(),
//                 Employee.get().getName(),
//                 Employee.get().getEmail(),
//                 Employee.get().getContact(),
//                 Employee.get().getCreatedAt(),
//                 Employee.get().isActive(),
//                 Employee.get().getRole(),
//                 Employee.get().getInstitution()
//             );

//             var token = this.authService.generateToken(account);
//             return ResponseEntity.status(HttpStatus.OK).body(
//                 new LogInEmployeeResponseDto(token, null)  
//             );
            
//         } catch (Exception e) {
            
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                 new LogInEmployeeResponseDto(null, e.getMessage())  
//             );
            
//         } 
        


        
//     }
   
// }
