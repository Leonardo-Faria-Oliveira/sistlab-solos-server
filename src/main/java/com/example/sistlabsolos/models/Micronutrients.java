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
@Table(name = "micronutrientes")
public class Micronutrients implements Serializable{
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Double boron;

    @NotNull
    private Double copper;

    @NotNull
    private Double iron;

    @NotNull
    private Double manganese;

    @NotNull
    private Double zinc;

    @OneToOne
    @JoinColumn(name = "chemicalPhysicalReport.reportId")
    private ChemicalPhysicalReport chemicalPhysicalReport;

    public Micronutrients(UUID id, @NotNull Double boron, @NotNull Double copper, @NotNull Double iron,
            @NotNull Double manganese, @NotNull Double zinc) {
        this.id = id;
        this.boron = boron;
        this.copper = copper;
        this.iron = iron;
        this.manganese = manganese;
        this.zinc = zinc;
    }

    public Micronutrients(@NotNull Double boron, @NotNull Double copper, @NotNull Double iron,
    @NotNull Double manganese, @NotNull Double zinc) {

        this.boron = boron;
        this.copper = copper;
        this.iron = iron;
        this.manganese = manganese;
        this.zinc = zinc;

    }

    public Micronutrients() {

        this.boron = 0.0;
        this.copper = 0.0;
        this.iron = 0.0;
        this.manganese = 0.0;
        this.zinc = 0.0;

    }

}
