package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    public List<Employee> findByOrderByCreatedAtDesc();
    public List<Employee> findByLabOrderByCreatedAtDesc(Lab lab);
    public List<Employee> findByLabOrderByNameDesc(Lab lab);
    public List<Employee> findByLabOrderByNameAsc(Lab lab);
    public List<Employee> findTop3ByLabAndNameContainingIgnoreCase(Lab lab, String name);
    public List<Employee> findByLabOrderByEmailDesc(Lab lab);
    public List<Employee> findByLabOrderByEmailAsc(Lab lab);
    public List<Employee> findTop3ByLabAndEmailContainingIgnoreCase(Lab lab, String email);
    public List<Employee> findByLabOrderByJobDesc(Lab lab);
    public List<Employee> findByLabOrderByJobAsc(Lab lab);
    public List<Employee> findTop3ByLabAndJobContainingIgnoreCase(Lab lab, String job);
    Optional<Employee> findByEmail(String email);

}
