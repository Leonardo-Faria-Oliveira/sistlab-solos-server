package com.example.sistlabsolos.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "laudos_quimico_fisicos")
public class ChemicalPhysicalReport extends Report {

    @NotNull
    private Double acidity;
     
    @NotNull
    private Double phosphor;

    @NotNull
    private Double ctc;

    @NotNull
    private Double bases;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "saturation_id", referencedColumnName = "id")
    private Saturation saturation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "physicalAnalysis_id", referencedColumnName = "id")
    private PhysicalAnalysis physicalAnalysis;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chemicalAnalysis_id", referencedColumnName = "id")
    private ChemicalAnalysis chemicalAnalysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "phosphorValue.id")
    private PhosphorValue phosphorValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "micronutrient_id", referencedColumnName = "id")
    private Micronutrients micronutrients;

    @OneToMany(mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Employee_Reports> employeesList = new ArrayList<>();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client.id")
    private Client client;

    public ChemicalPhysicalReport(UUID reportId, @NotBlank String landName, @NotBlank String field,
            @NotNull Double depth, LocalDateTime createdAt, @NotNull Double acidity, Double phosphor, Double ctc,
            Double bases, Saturation saturation, PhysicalAnalysis physicalAnalysis, ChemicalAnalysis chemicalAnalysis,
            PhosphorValue phosphorValue, Micronutrients micronutrients, Employee employee,
            Employee technicalResponsible, Client client, Lab lab) {

        super(reportId, landName, field, depth, createdAt);
        this.acidity = acidity;
        this.phosphor = phosphor;
        this.ctc = ctc;
        this.bases = bases;
        this.saturation = saturation;
        this.physicalAnalysis = physicalAnalysis;
        this.chemicalAnalysis = chemicalAnalysis;
        this.phosphorValue = phosphorValue;
        this.micronutrients = micronutrients;
        this.client = client;
        this.lab = lab;
    
    }

    public ChemicalPhysicalReport(UUID reportId, @NotBlank String landName, @NotNull Double lat, @NotNull Double lng, @NotBlank String field,
    @NotNull Double depth, LocalDateTime createdAt, @NotNull Double acidity, Double phosphor, Double ctc,
    Double bases, Saturation saturation, PhysicalAnalysis physicalAnalysis, ChemicalAnalysis chemicalAnalysis,
    PhosphorValue phosphorValue, Micronutrients micronutrients, Employee employee,
    Employee technicalResponsible, Client client, Lab lab) {

        super(reportId, landName, field, depth, createdAt, lat, lng);
        this.acidity = acidity;
        this.phosphor = phosphor;
        this.ctc = ctc;
        this.bases = bases;
        this.saturation = saturation;
        this.physicalAnalysis = physicalAnalysis;
        this.chemicalAnalysis = chemicalAnalysis;
        this.phosphorValue = phosphorValue;
        this.micronutrients = micronutrients;
        this.client = client;
        this.lab = lab;
        


    }

    public ChemicalPhysicalReport(
        @NotBlank String landName, 
        @NotBlank String city,
        @NotBlank String field,
        @NotNull Double depth, 
        LocalDateTime createdAt, 
        @NotNull Double acidity, 
        @NotNull Double phosphor, 
        @NotNull Double ctc,
        @NotNull Double bases, 
        @NotNull Saturation saturation, 
        @NotNull PhysicalAnalysis physicalAnalysis, 
        @NotNull ChemicalAnalysis chemicalAnalysis,
        @NotNull PhosphorValue phosphorValue, 
        @NotNull Employee employee,
        @NotNull Employee technicalResponsible, 
        @NotNull Client client,
        @NotNull Lab lab,
        Micronutrients micronutrients
        ) {

        super(landName, field, depth, createdAt, city);
        this.acidity = acidity;
        this.phosphor = phosphor;
        this.ctc = ctc;
        this.bases = bases;
        this.saturation = saturation;
        this.physicalAnalysis = physicalAnalysis;
        this.chemicalAnalysis = chemicalAnalysis;
        this.phosphorValue = phosphorValue;
        this.micronutrients = micronutrients;
        this.client = client;
        this.lab = lab;
        this.employeesList.add(new Employee_Reports(employee, null));
        this.employeesList.add(new Employee_Reports(technicalResponsible, null));
        

    }

    public ChemicalPhysicalReport(
        @NotBlank String landName, 
        @NotBlank String city,
        @NotBlank String field,
        @NotNull Double depth, 
        LocalDateTime createdAt, 
        @NotNull Double acidity, 
        @NotNull Double phosphor, 
        @NotNull Double ctc,
        @NotNull Double bases, 
        @NotNull Saturation saturation, 
        @NotNull PhysicalAnalysis physicalAnalysis, 
        @NotNull ChemicalAnalysis chemicalAnalysis,
        @NotNull PhosphorValue phosphorValue, 
        @NotNull Employee employee,
        @NotNull Employee technicalResponsible, 
        @NotNull Client client,
        @NotNull Lab lab,
        Micronutrients micronutrients,
        Double lat,
        Double lng  
        ) {

        super(landName, field, depth, createdAt, city, lat, lng);
        this.acidity = acidity;
        this.phosphor = phosphor;
        this.ctc = ctc;
        this.bases = bases;
        this.saturation = saturation;
        this.physicalAnalysis = physicalAnalysis;
        this.chemicalAnalysis = chemicalAnalysis;
        this.phosphorValue = phosphorValue;
        this.micronutrients = micronutrients;
        this.client = client;
        this.lab = lab;
        this.employeesList.add(new Employee_Reports(employee, null));
        this.employeesList.add(new Employee_Reports(technicalResponsible, null));
        

    }

    public ChemicalPhysicalReport() {

        super("", "", 0.0, LocalDateTime.now(), "");
        this.acidity = 0.0;
        this.phosphor = 0.0;
        this.ctc = 0.0;
        this.bases = 0.0;
        this.saturation = new Saturation();
        this.physicalAnalysis = new PhysicalAnalysis();
        this.chemicalAnalysis = new ChemicalAnalysis();
        this.phosphorValue = new PhosphorValue();
        this.micronutrients = new Micronutrients();
        this.client = new Client();
        this.lab = new Lab();

    }
    
}
