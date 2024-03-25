package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.EmployeeAbstract;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.EmployeeRepository;


@Service
public class EmployeeService extends EmployeeAbstract {
    
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )   
    public Employee create(Employee employee) throws SQLException{

        var alreadyBeenInserted = this.employeeRepository.findByEmail(employee.getEmail());
        if(alreadyBeenInserted.isEmpty()){

            return this.employeeRepository.save(employee);

        }
        
        return null;
        
    
    }

    @Override
    public List<Employee> list() {

        return this.employeeRepository.findByOrderByCreatedAtDesc();

    }

    @Override
    public List<Employee> getEmployeesByLab(Lab lab) {

        return this.employeeRepository.findByLabOrderByCreatedAtDesc(lab);

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
    public Employee firstAccessEmployeeUpdate(Employee employee, String password) {
       
       

        employee.setPassword(password);
        employee.setActive(true);
        
        this.employeeRepository.save(employee);

        return employee;

    }

    @Override
    public List<Employee> getEmployeesByNameDesc(Lab lab) {

        return this.employeeRepository.findByLabOrderByNameDesc(lab);
        
    }

    @Override
    public List<Employee> getEmployeesByNameAsc(Lab lab) {

        return this.employeeRepository.findByLabOrderByNameAsc(lab);

    }

    @Override
    public List<Employee> getEmployeesByNameSearch(Lab lab, String name) {

        return this.employeeRepository.findTop3ByLabAndByNameContainingIgnoreCase(lab, name);
    
    }

    @Override
    public List<Employee> getEmployeesByEmailDesc(Lab lab) {

        return this.employeeRepository.findByLabOrderByEmailDesc(lab);

    }

    @Override
    public List<Employee> getEmployeesByEmailAsc(Lab lab) {

        return this.employeeRepository.findByLabOrderByEmailAsc(lab);

    }

    @Override
    public List<Employee> getEmployeesByEmailSearch(Lab lab, String email) {
        
        return this.employeeRepository.findTop3ByLabAndByEmailContainingIgnoreCase(lab, email);
    
    }

    @Override
    public List<Employee> getEmployeesByJobDesc(Lab lab) {
        
        return this.employeeRepository.findByLabOrderByJobDesc(lab);
        
    }

    @Override
    public List<Employee> getEmployeesByJobAsc(Lab lab) {
                
        return this.employeeRepository.findByLabOrderByJobAsc(lab);
        
    }

    @Override
    public List<Employee> getEmployeesByJobSearch(Lab lab, String job) {
        
        return this.employeeRepository.findTop3ByLabAndByJobContainingIgnoreCase(lab, job);
    
    }

    @Override
    public Employee update(UUID id, Employee obj) {
       
        var updatedEmployee = this.employeeRepository.findById(id);

        if(updatedEmployee.isEmpty()){
            return null;
        }
        
        updatedEmployee.get().setName(obj.getName());
        updatedEmployee.get().setEmail(obj.getEmail());
        updatedEmployee.get().setContact(obj.getContact());
        updatedEmployee.get().setCrea(obj.getCrea());
        updatedEmployee.get().setJob(obj.getJob());
        updatedEmployee.get().setPassword(obj.getPassword());
    
        this.employeeRepository.save(updatedEmployee.get());

        return updatedEmployee.get();
        
    }

    @Override
    public Employee setEmployeeStatus(UUID id, boolean status) {
       
        var employee = this.employeeRepository.findById(id);
        if(employee.isEmpty()){
            return null;
        }

        employee.get().setActive(status);

        this.employeeRepository.save(employee.get());

        return employee.get();

    }


   

}
