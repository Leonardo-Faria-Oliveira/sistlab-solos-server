package com.example.sistlabsolos.controllers;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.sistlabsolos.dtos.chemicalPhysicalReport.CreateChemicalPhysicalReportRequestDto;
import com.example.sistlabsolos.dtos.chemicalPhysicalReport.CreateChemicalPhysicalReportResponseDto;
import com.example.sistlabsolos.dtos.chemicalPhysicalReport.GetChemicalPhysicalReportByIdDto;
import com.example.sistlabsolos.dtos.chemicalPhysicalReport.GetChemicalPhysicalReportsDto;
import com.example.sistlabsolos.models.ChemicalAnalysis;
import com.example.sistlabsolos.models.ChemicalPhysicalReport;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.Micronutrients;
import com.example.sistlabsolos.models.PhysicalAnalysis;
import com.example.sistlabsolos.models.Saturation;
import com.example.sistlabsolos.services.ChemicalPhysicalReportService;
import com.example.sistlabsolos.services.ClientService;
import com.example.sistlabsolos.services.EmployeeService;
import com.example.sistlabsolos.services.LabService;
import com.example.sistlabsolos.services.PhosphorValueService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/")
public class ChemicalPhysicalReportController {

    @Autowired
    ChemicalPhysicalReportService chemicalPhysicalReportService;

    @Autowired
    LabService labService;

    @Autowired
    ClientService clientService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PhosphorValueService phosphorValueService;

    @PostMapping("client/{clientId}/report/chemical_physical")
      public ResponseEntity<CreateChemicalPhysicalReportResponseDto> createChemicalPhysicalReport(
        @PathVariable UUID clientId,  
      @RequestBody @Valid CreateChemicalPhysicalReportRequestDto createChemicalPhysicalReportDto){
        
        try {

            Lab lab = this.labService.getLabByName(createChemicalPhysicalReportDto.labName());
            if(lab == null){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Laboratorio não existe"
                    )
                );
                
            }

            var employee = this.employeeService.getEmployeeByEmail(
                createChemicalPhysicalReportDto.employeeEmail()
            );

            if(employee.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Funcionario não existe"
                    )
                );
            }

            var technicalResponsible = this.employeeService.getEmployeeByEmail(
                createChemicalPhysicalReportDto.technicalResponsibleEmail()
            );

            if(technicalResponsible.isEmpty() || technicalResponsible.get().getCrea() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Responsavel tecnico não existe"
                    )
                );
            }

            var client = this.clientService.getClientById(
                clientId
            );
            if(client.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Cliente não existe"
                    )
                );
            }

            var phosphorValue = this.phosphorValueService.getLastPhosphorValue(
                lab
            );
            if(phosphorValue == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Laudo não pode ser criado"
                    )
                );
            }

            var saturation = new Saturation(
                createChemicalPhysicalReportDto.aluminumPercent(),
                createChemicalPhysicalReportDto.calciumPercent(),
                createChemicalPhysicalReportDto.potassiumPercent(),
                createChemicalPhysicalReportDto.magnesiumPercent(),
                createChemicalPhysicalReportDto.basesPercent()
            );

            var physicalAnalysis = new PhysicalAnalysis(
                createChemicalPhysicalReportDto.sand(),
                createChemicalPhysicalReportDto.clay(),
                createChemicalPhysicalReportDto.silt(),
                createChemicalPhysicalReportDto.organicMatter(),
                createChemicalPhysicalReportDto.totalOrganicCarbon(),
                createChemicalPhysicalReportDto.carbon()
            );

            var chemicalAnalysis = new ChemicalAnalysis(
                createChemicalPhysicalReportDto.ph(),
                createChemicalPhysicalReportDto.sulfur(),
                createChemicalPhysicalReportDto.potassium(),
                createChemicalPhysicalReportDto.calcium(),
                createChemicalPhysicalReportDto.magnesium(),
                createChemicalPhysicalReportDto.aluminum(),
                createChemicalPhysicalReportDto.sodium(),
                createChemicalPhysicalReportDto.phosphorAbsorbance(),
                createChemicalPhysicalReportDto.potencialAcidity()
            );

            var micronutrients = new Micronutrients(
                createChemicalPhysicalReportDto.boron(),
                createChemicalPhysicalReportDto.copper(),
                createChemicalPhysicalReportDto.iron(),
                createChemicalPhysicalReportDto.manganese(),
                createChemicalPhysicalReportDto.zinc()
            );

            ChemicalPhysicalReport res = this.chemicalPhysicalReportService.create(
                new ChemicalPhysicalReport(
                    createChemicalPhysicalReportDto.landName(),
                    createChemicalPhysicalReportDto.city(),
                    createChemicalPhysicalReportDto.field(),
                    createChemicalPhysicalReportDto.depth(),
                    LocalDateTime.now(),
                    createChemicalPhysicalReportDto.acidity(),
                    createChemicalPhysicalReportDto.phosphor(),
                    createChemicalPhysicalReportDto.ctc(),
                    createChemicalPhysicalReportDto.bases(),
                    saturation,
                    physicalAnalysis,
                    chemicalAnalysis,
                    phosphorValue,
                    employee.get(),
                    technicalResponsible.get(),
                    client.get(),
                    lab,
                    micronutrients,
                    createChemicalPhysicalReportDto.lat(),
                    createChemicalPhysicalReportDto.lng()
                )
            );
            
            if(res == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new CreateChemicalPhysicalReportResponseDto(
                        null, "Houve um erro, tente novamente"
                    )
                );
            }
            res.setLab(null);
            res.setEmployeesList(null);
            res.setClient(null);
            res.setPhosphorValue(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new CreateChemicalPhysicalReportResponseDto(res, null)
            );

            
        } catch (Exception e) {
            // System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CreateChemicalPhysicalReportResponseDto(null, e.getMessage())
            );

        }
        
    }

    @GetMapping("lab/{labName}/reports")
    public ResponseEntity<GetChemicalPhysicalReportsDto> getChemicalPhysicalReports(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetChemicalPhysicalReportsDto(null, "Laboratorio não encontrado")
                );
            }

            var res = this.chemicalPhysicalReportService.getChemicalPhysicalReportsByLab(lab);

            for(ChemicalPhysicalReport report : res){
                report.getClient().setChemicalPhysicalReportList(null);
                report.getClient().setLab(null);

                report.setChemicalAnalysis(null);
                report.setMicronutrients(null);
                report.setPhysicalAnalysis(null);
                report.setSaturation(null);

                report.getEmployeesList().get(0).setChemicalPhysicalReport(null);
                report.getEmployeesList().get(0).getEmployee().setReportsList(null);
                report.getEmployeesList().get(0).getEmployee().setLab(null);
                
                report.getEmployeesList().get(1).getEmployee().setReportsList(null);
                report.getEmployeesList().get(1).getEmployee().setLab(null);
                report.getEmployeesList().get(1).setChemicalPhysicalReport(null);
                

                report.setLab(null);
                
                report.setPhosphorValue(null);

            }
     
            


            return ResponseEntity.status(HttpStatus.OK).body(
                new GetChemicalPhysicalReportsDto(res, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetChemicalPhysicalReportsDto(null, e.getMessage()));
            
        }


    }

    
    @GetMapping("lab/{labName}/report/{reportId}")
    public ResponseEntity<GetChemicalPhysicalReportByIdDto> getChemicalPhysicalReportsByNameDesc(
        @PathVariable String labName,
        @PathVariable UUID reportId
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetChemicalPhysicalReportByIdDto(null, "Laboratorio não encontrado")
                );
            }

            var report = this.chemicalPhysicalReportService.getChemicalPhysicalReportById(reportId);
            if(report.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetChemicalPhysicalReportByIdDto(null, "Laudo não encontrado")
                );
            }

            report.get().getChemicalAnalysis().setChemicalPhysicalReport(null);
            report.get().getMicronutrients().setChemicalPhysicalReport(null);
            report.get().getPhysicalAnalysis().setChemicalPhysicalReport(null);
            report.get().getSaturation().setChemicalPhysicalReport(null);
            
            report.get().getClient().setChemicalPhysicalReportList(null);
            report.get().getClient().setLab(null);

            report.get().getEmployeesList().get(0).setChemicalPhysicalReport(null);
            report.get().getEmployeesList().get(0).getEmployee().setReportsList(null);
            report.get().getEmployeesList().get(0).getEmployee().setLab(null);
            
            report.get().getEmployeesList().get(1).getEmployee().setReportsList(null);
            report.get().getEmployeesList().get(1).getEmployee().setLab(null);
            report.get().getEmployeesList().get(1).setChemicalPhysicalReport(null);
            
            report.get().setLab(null);
            
            report.get().setPhosphorValue(null);

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetChemicalPhysicalReportByIdDto(report, null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new GetChemicalPhysicalReportByIdDto(null, e.getMessage())
            );
            
        }

    }

    @GetMapping("{labName}/landname/asc")
    public ResponseEntity<GetChemicalPhysicalReportsDto> getChemicalPhysicalReportsByNameAsc(
        @PathVariable String labName
    ){

        try {

            var lab = this.labService.getLabByName(labName);

            if(lab == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new GetChemicalPhysicalReportsDto(null, "Laboratorio não encontrado")
                );
            }

            return ResponseEntity.status(HttpStatus.OK).body(
                new GetChemicalPhysicalReportsDto(this.chemicalPhysicalReportService.getChemicalPhysicalReportsByLandNameAsc(lab), null)
            );
            
        } catch (Exception e) {
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GetChemicalPhysicalReportsDto(null, e.getMessage()));
            
        }

    }

   
}
