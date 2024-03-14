package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    public List<Employee> findByOrderByCreatedAtEmployeeDesc();
    Optional<Employee> findByEmail(String email);

}
