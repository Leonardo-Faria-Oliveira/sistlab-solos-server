package com.example.sistlabsolos.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.sistlabsolos.abstracts.ChemicalPhysicalReportAbstract;
import com.example.sistlabsolos.models.ChemicalPhysicalReport;
import com.example.sistlabsolos.models.Employee_Reports;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.repositories.ChemicalPhysicalReportRepository;
import com.example.sistlabsolos.repositories.Employee_ReportsRepository;

@Service
public class ChemicalPhysicalReportService extends ChemicalPhysicalReportAbstract {

    @Autowired
    ChemicalPhysicalReportRepository chemicalPhysicalReportRepository;

    @Autowired
    Employee_ReportsRepository employee_ReportsRepository;

    @Override
    @Transactional(
        readOnly = false,
        propagation = Propagation.SUPPORTS,
        rollbackFor = {SQLException.class}
    )   
    public ChemicalPhysicalReport create(ChemicalPhysicalReport obj)throws SQLException  {
        
        // System.out.println(obj.getEmployeesList().get(0).getEmployee().getName());
        // System.out.println(obj);
        var report = this.chemicalPhysicalReportRepository.save(obj);

        this.employee_ReportsRepository.save(new Employee_Reports(
            report.getEmployeesList().get(0).getEmployee(),
            report
        ));

        if(report.getEmployeesList().get(0).getEmployee() == null){
            this.employee_ReportsRepository.save(new Employee_Reports(
        null,
                report
            ));
        }else{
            
            this.employee_ReportsRepository.save(new Employee_Reports(
                report.getEmployeesList().get(1).getEmployee(),
                report
            ));
            
        }

        return report;
        
    }

    @Override
    public List<ChemicalPhysicalReport> list() {
        
        return this.chemicalPhysicalReportRepository.findAll();
        
    }

    @Override
    public ChemicalPhysicalReport update(UUID id, ChemicalPhysicalReport obj) {
       
        
        var report = this.chemicalPhysicalReportRepository.findById(id);
        if(report.isEmpty()){
            return null;
        }

        return report.get();

    }

    @Override
    public List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameDesc(Lab lab) {
        
        return this.chemicalPhysicalReportRepository.findByLabOrderByLandNameDesc(lab);
        
    }

    @Override
    public List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameAsc(Lab lab) {
    
        return this.chemicalPhysicalReportRepository.findByLabOrderByLandNameAsc(lab);
        
    }


    @Override
    public List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameSearch(Lab lab, String LandName) {
        
       return this.chemicalPhysicalReportRepository.findTop3ByLabAndLandNameContainingIgnoreCase(lab, LandName);
        
    }

    @Override
    public Optional<ChemicalPhysicalReport> getChemicalPhysicalReportById(UUID ChemicalPhysicalReportId) {
        
        return this.chemicalPhysicalReportRepository.findById(ChemicalPhysicalReportId);
        
    }

    @Override
    public List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLab(Lab lab) {
        
        return this.chemicalPhysicalReportRepository.findByLabOrderByCreatedAtDesc(lab);
        
    }
    
}
