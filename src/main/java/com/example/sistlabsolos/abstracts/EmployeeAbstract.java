package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class EmployeeAbstract implements IDAO<Employee> {
    public abstract List<Employee> getEmployeesByNameDesc(Lab lab);
    public abstract List<Employee> getEmployeesByNameAsc(Lab lab);
    public abstract List<Employee> getEmployeesByNameSearch(Lab lab, String name);
    public abstract List<Employee> getEmployeesByEmailDesc(Lab lab);
    public abstract List<Employee> getEmployeesByEmailAsc(Lab lab);
    public abstract List<Employee> getEmployeesByEmailSearch(Lab lab, String email);
    public abstract List<Employee> getEmployeesByJobDesc(Lab lab);
    public abstract List<Employee> getEmployeesByJobAsc(Lab lab);
    public abstract List<Employee> getEmployeesByJobSearch(Lab lab, String job);
    public abstract Optional<Employee> getEmployeeById(UUID employeeId);
    public abstract Optional<Employee> getEmployeeByEmail(String email);
    public abstract List<Employee> getEmployeesByLab(Lab lab);
    public abstract Employee firstAccessEmployeeUpdate(Employee employee, String password);
    public abstract Employee setEmployeeStatus(UUID id, boolean status);
}
