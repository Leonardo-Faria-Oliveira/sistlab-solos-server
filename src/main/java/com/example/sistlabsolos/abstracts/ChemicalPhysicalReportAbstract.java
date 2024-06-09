package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.ChemicalPhysicalReport;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class ChemicalPhysicalReportAbstract implements IDAO<ChemicalPhysicalReport> {
    
    public abstract List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameDesc(Lab lab);
    public abstract List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameAsc(Lab lab);
    public abstract List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLandNameSearch(Lab lab, String LandName);
    public abstract Optional<ChemicalPhysicalReport> getChemicalPhysicalReportById(UUID ChemicalPhysicalReportId);
    public abstract List<ChemicalPhysicalReport> getChemicalPhysicalReportsByLab(Lab lab);

}
