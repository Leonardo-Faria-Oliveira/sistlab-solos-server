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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "analise_fisica")
public class PhysicalAnalysis implements Serializable {
      
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Double sand;

    @NotNull
    private Double clay;

    @NotNull
    private Double silt;

    @NotNull
    private Double organicMatter;

    @OneToOne
    @JoinColumn(name = "chemicalPhysicalReport.reportId")
    private ChemicalPhysicalReport chemicalPhysicalReport;

    public PhysicalAnalysis(UUID id, @NotBlank Double sand, @NotBlank Double clay, @NotNull Double silt,
            @NotNull Double organicMatter) {
        this.id = id;
        this.sand = sand;
        this.clay = clay;
        this.silt = silt;
        this.organicMatter = organicMatter;
    }

    public PhysicalAnalysis(@NotBlank Double sand, @NotBlank Double clay, @NotNull Double silt,
    @NotNull Double organicMatter) {

        this.sand = sand;
        this.clay = clay;
        this.silt = silt;
        this.organicMatter = organicMatter;
        
    }

    public PhysicalAnalysis() {

        this.sand = 0.0;
        this.clay = 0.0;
        this.silt = 0.0;
        this.organicMatter = 0.0;
        
    }

    



}
