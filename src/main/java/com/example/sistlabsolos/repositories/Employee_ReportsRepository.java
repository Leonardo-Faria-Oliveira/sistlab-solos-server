package com.example.sistlabsolos.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Employee_Reports;

@Repository
public interface Employee_ReportsRepository  extends JpaRepository<Employee_Reports, UUID>{
    
}
