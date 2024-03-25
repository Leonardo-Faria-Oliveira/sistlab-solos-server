package com.example.sistlabsolos.models;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "entradas_quimico_fisicos")
public class ChemicalPhysicalReport extends Report {

    @NotNull
    private Double acidity;
     
    private Double phosphor;

    private Double ctc;

    private Double bases;
     
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY)
    private Saturation saturation;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY)
    private PhysicalAnalysis physicalAnalysis;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY)
    private ChemicalAnalysis chemicalAnalysis;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY)
    private PhosphorValue phosphorValue;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "chemicalPhysicalReport", fetch = FetchType.LAZY)
    private Micronutrients micronutrients;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee.id", insertable=false, updatable=false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee.id", insertable=false, updatable=false)
    private Employee technicalResponsible;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client.id")
    private Client client;

    public ChemicalPhysicalReport(UUID reportId, @NotBlank String landName, @NotBlank String field,
            @NotNull Double depth, LocalDateTime createdAt, @NotNull Double acidity, Double phosphor, Double ctc,
            Double bases, Saturation saturation, PhysicalAnalysis physicalAnalysis, ChemicalAnalysis chemicalAnalysis,
            PhosphorValue phosphorValue, Micronutrients micronutrients, Employee employee,
            Employee technicalResponsible, Client client) {

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
        this.employee = employee;
        this.technicalResponsible = technicalResponsible;
        this.client = client;
    
    }

    public ChemicalPhysicalReport(@NotBlank String landName, @NotBlank String field,
    @NotNull Double depth, LocalDateTime createdAt, @NotNull Double acidity, Double phosphor, Double ctc,
    Double bases, Saturation saturation, PhysicalAnalysis physicalAnalysis, ChemicalAnalysis chemicalAnalysis,
    PhosphorValue phosphorValue, Micronutrients micronutrients, Employee employee,
    Employee technicalResponsible, Client client) {

        super(landName, field, depth, createdAt);
        this.acidity = acidity;
        this.phosphor = phosphor;
        this.ctc = ctc;
        this.bases = bases;
        this.saturation = saturation;
        this.physicalAnalysis = physicalAnalysis;
        this.chemicalAnalysis = chemicalAnalysis;
        this.phosphorValue = phosphorValue;
        this.micronutrients = micronutrients;
        this.employee = employee;
        this.technicalResponsible = technicalResponsible;
        this.client = client;

    }

    public ChemicalPhysicalReport() {

        super("", "", 0.0, LocalDateTime.now());
        this.acidity = 0.0;
        this.phosphor = 0.0;
        this.ctc = 0.0;
        this.bases = 0.0;
        this.saturation = new Saturation();
        this.physicalAnalysis = new PhysicalAnalysis();
        this.chemicalAnalysis = new ChemicalAnalysis();
        this.phosphorValue = new PhosphorValue();
        this.micronutrients = new Micronutrients();
        this.employee = new Employee();
        this.technicalResponsible = new Employee();
        this.client = new Client();

    }
    
}
