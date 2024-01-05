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

        var Employee = new Employee(
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
        if(alreadyBeenInserted == null){

            return this.employeeRepository.save(Employee);

        }
        
        return null;
        
    
    }

    @Override
    public List<Employee> getEmployees() {

        return this.employeeRepository.findAll();

    }

    @Override
    public Optional<Employee> getEmployeeById(UUID EmployeeId){
        
        return this.employeeRepository.findById(EmployeeId);
     
    }

    @Override
    public Optional<Employee> getEmployeeByEmail(String email) {
        
        return this.employeeRepository.findByEmail(email);

    }

   

}
