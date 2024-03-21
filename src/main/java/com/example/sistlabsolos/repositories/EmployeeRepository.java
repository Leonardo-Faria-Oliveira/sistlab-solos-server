package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    public List<Employee> findByOrderByCreatedAtDesc();
    public List<Employee> findByOrderByNameDesc();
    public List<Employee> findByOrderByNameAsc();
    public List<Employee> findTop3ByNameContainingIgnoreCase(String name);
    public List<Employee> findByOrderByEmailDesc();
    public List<Employee> findByOrderByEmailAsc();
    public List<Employee> findTop3ByEmailContainingIgnoreCase(String email);
    public List<Employee> findByOrderByJobDesc();
    public List<Employee> findByOrderByJobAsc();
    public List<Employee> findTop3ByJobContainingIgnoreCase(String job);
    Optional<Employee> findByEmail(String email);

}
