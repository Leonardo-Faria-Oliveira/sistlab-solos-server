package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Role;


@Repository
public abstract class EmployeeAbstract {
    public abstract Employee create(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        String job,
        Role role,
        Lab lab
    );
    public abstract Employee createTechnicalResponsible(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        String job,
        String crea,
        Role role,
        Lab lab
    );
    public abstract List<Employee> getEmployees();
    public abstract List<Employee> getEmployeesByNameDesc();
    public abstract List<Employee> getEmployeesByNameAsc();
    public abstract List<Employee> getEmployeesByNameSearch(String name);
    public abstract List<Employee> getEmployeesByEmailDesc();
    public abstract List<Employee> getEmployeesByEmailAsc();
    public abstract List<Employee> getEmployeesByEmailSearch(String email);
    public abstract List<Employee> getEmployeesByJobDesc();
    public abstract List<Employee> getEmployeesByJobAsc();
    public abstract List<Employee> getEmployeesByJobSearch(String job);
    public abstract Optional<Employee> getEmployeeById(UUID employeeId);
    public abstract Optional<Employee> getEmployeeByEmail(String email);
    public abstract Employee firstAccessEmployeeUpdate(Employee employee, String password);
}
