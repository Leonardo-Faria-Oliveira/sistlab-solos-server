package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.ChemicalPhysicalReport;
import com.example.sistlabsolos.models.Employee;
import com.example.sistlabsolos.models.Lab;

@Repository
public interface ChemicalPhysicalReportRepository extends JpaRepository<ChemicalPhysicalReport, UUID> {

    public List<ChemicalPhysicalReport> findByLabOrderByCreatedAtDesc(Lab lab);
    public List<ChemicalPhysicalReport> findByLabOrderByLandNameDesc(Lab lab);
    public List<ChemicalPhysicalReport> findByLabOrderByLandNameAsc(Lab lab);
    public List<ChemicalPhysicalReport> findByLabOrderByCityDesc(Lab lab);
    public List<ChemicalPhysicalReport> findByLabOrderByCityAsc(Lab lab);
    public List<ChemicalPhysicalReport> findTop3ByLabAndLandNameContainingIgnoreCase(Lab lab, String landName);
    public List<ChemicalPhysicalReport> findTop3ByLabAndCityContainingIgnoreCase(Lab lab, String city);
    public List<ChemicalPhysicalReport> findByEmployeeOrderByCreatedAtDesc(Employee employee);
    public List<ChemicalPhysicalReport> findByEmployeeOrderByLandNameDesc(Employee employee);
    public List<ChemicalPhysicalReport> findByEmployeeOrderByLandNameAsc(Employee employee);
    public List<ChemicalPhysicalReport> findByEmployeeOrderByCityDesc(Employee employee);
    public List<ChemicalPhysicalReport> findByEmployeeOrderByCityAsc(Employee employee);
    public List<ChemicalPhysicalReport> findTop3ByEmployeeAndLandNameContainingIgnoreCase(Employee employee, String landName);
    public List<ChemicalPhysicalReport> findTop3ByEmployeeAndCityContainingIgnoreCase(Employee employee, String city);
    ChemicalPhysicalReport findByLandName(String landName);

}
