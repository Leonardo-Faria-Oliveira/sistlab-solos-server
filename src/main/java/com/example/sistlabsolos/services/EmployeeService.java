package com.example.sistlabsolos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sistlabsolos.abstracts.EmployeeAbstract;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;
import com.example.sistlabsolos.repositories.EmployeeRepository;


@Service
public class EmployeeService extends EmployeeAbstract {
    
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee create(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        String job,
        Role role,
        Lab lab
    ){

        var employee = new Employee(
            name, 
            email,
            password,
            contact,
            createdAt,
            active,
            job,
            role,
            lab
        );

        var alreadyBeenInserted = this.employeeRepository.findByEmail(email);
        if(alreadyBeenInserted.isEmpty()){

            return this.employeeRepository.save(employee);

        }
        
        return null;
        
    
    }

    @Override
    public List<Employee> getEmployees() {

        return this.employeeRepository.findByOrderByCreatedAtDesc();

    }
    



    @Override
    public Optional<Employee> getEmployeeById(UUID EmployeeId){
        
        return this.employeeRepository.findById(EmployeeId);
     
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        
        return this.employeeRepository.findByEmail(email);

    }

    @Override
    public Employee createTechnicalResponsible(String name, String email, String password, String contact,
            LocalDateTime createdAt, boolean active, String job, String crea, Role role, Lab lab) {
          
        var employee = new Employee(
            name, 
            email,
            password,
            contact,
            createdAt,
            active,
            job,
            crea,
            role,
            lab
        );

        var alreadyBeenInserted = this.employeeRepository.findByEmail(email);
        if(alreadyBeenInserted.isEmpty()){

            return this.employeeRepository.save(employee);

        }
        
        return null;
    }

    @Override
    public Employee firstAccessEmployeeUpdate(Employee employee, String password) {
       
       

        employee.setPassword(password);
        employee.setActive(true);
        
        this.employeeRepository.save(employee);

        return employee;

    }

    @Override
    public List<Employee> getEmployeesByNameDesc() {

        return this.employeeRepository.findByOrderByNameDesc();
        
    }

    @Override
    public List<Employee> getEmployeesByNameAsc() {

        return this.employeeRepository.findByOrderByNameAsc();

    }

    @Override
    public List<Employee> getEmployeesByNameSearch(String name) {

        return this.employeeRepository.findTop3ByNameContainingIgnoreCase(name);
    
    }

    @Override
    public List<Employee> getEmployeesByEmailDesc() {

        return this.employeeRepository.findByOrderByEmailDesc();

    }

    @Override
    public List<Employee> getEmployeesByEmailAsc() {

        return this.employeeRepository.findByOrderByEmailAsc();

    }

    @Override
    public List<Employee> getEmployeesByEmailSearch(String email) {
        
        return this.employeeRepository.findTop3ByEmailContainingIgnoreCase(email);
    
    }

    @Override
    public List<Employee> getEmployeesByJobDesc() {
        
        return this.employeeRepository.findByOrderByJobDesc();
        
    }

    @Override
    public List<Employee> getEmployeesByJobAsc() {
                
        return this.employeeRepository.findByOrderByJobAsc();
        
    }

    @Override
    public List<Employee> getEmployeesByJobSearch(String job) {
        
        return this.employeeRepository.findTop3ByJobContainingIgnoreCase(job);
    
    }

   

}
