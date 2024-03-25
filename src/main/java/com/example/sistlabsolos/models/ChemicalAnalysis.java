package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "analise_quimica")
public class ChemicalAnalysis implements Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Double ph;

    @NotNull
    private Double sulfur;

    @NotNull
    private Double potassium;

    @NotNull
    private Double calcium;

    @NotNull
    private Double magnesium;

    @NotNull
    private Double aluminum;

    @NotNull
    private Double sodium;

    @NotNull
    private Double phosphorAbsorbance;

    @OneToOne
    @JoinColumn(name = "chemicalPhysicalReport.reportId")
    private ChemicalPhysicalReport chemicalPhysicalReport;

    public ChemicalAnalysis(@NotNull UUID id, @NotNull Double ph, @NotNull Double sulfur, @NotNull Double potassium,
            @NotNull Double calcium, @NotNull Double magnesium, @NotNull Double aluminum,
            @NotNull Double sodium, @NotNull Double phosphorAbsorbance) {
        
        this.id = id;
        this.ph = ph;
        this.sulfur = sulfur;
        this.potassium = potassium;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.aluminum = aluminum;
        this.sodium = sodium;
        this.phosphorAbsorbance = phosphorAbsorbance;
    }

    public ChemicalAnalysis(@NotNull Double ph, @NotNull Double sulfur, @NotNull Double potassium,
    @NotNull Double calcium, @NotNull Double magnesium, @NotNull Double aluminum,
    @NotNull Double sodium, @NotNull Double phosphorAbsorbance) {
        this.ph = ph;
        this.sulfur = sulfur;
        this.potassium = potassium;
        this.calcium = calcium;
        this.magnesium = magnesium;
        this.aluminum = aluminum;
        this.sodium = sodium;
        this.phosphorAbsorbance = phosphorAbsorbance;
    }

    public ChemicalAnalysis() {

        this.ph = 0.0;
        this.sulfur = 0.0;
        this.potassium = 0.0;
        this.calcium = 0.0;
        this.magnesium = 0.0;
        this.aluminum = 0.0;
        this.sodium = 0.0;
        this.phosphorAbsorbance = 0.0;

    }

}
