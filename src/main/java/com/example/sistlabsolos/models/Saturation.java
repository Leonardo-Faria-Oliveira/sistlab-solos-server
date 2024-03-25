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
@Table(name = "saturacao")
public class Saturation implements Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Double aluminumPercent;

    @NotNull
    private Double calciumPercent;

    @NotNull
    private Double potassiumPercent;

    @NotNull
    private Double magnesiumPercent;

    @NotNull
    private Double basesPercent;

    @OneToOne
    @JoinColumn(name = "chemicalPhysicalReport.reportId")
    private ChemicalPhysicalReport chemicalPhysicalReport;

    public Saturation(UUID id, @NotNull Double aluminumPercent, @NotNull Double calciumPercent,
            @NotNull Double potassiumPercent, @NotNull Double magnesiumPercent, @NotNull Double basesPercent) {
        
        this.id = id;
        this.aluminumPercent = aluminumPercent;
        this.calciumPercent = calciumPercent;
        this.potassiumPercent = potassiumPercent;
        this.magnesiumPercent = magnesiumPercent;
        this.basesPercent = basesPercent;
    
    }

    public Saturation(@NotNull Double aluminumPercent, @NotNull Double calciumPercent,
    @NotNull Double potassiumPercent, @NotNull Double magnesiumPercent, @NotNull Double basesPercent) {
      
        this.aluminumPercent = aluminumPercent;
        this.calciumPercent = calciumPercent;
        this.potassiumPercent = potassiumPercent;
        this.magnesiumPercent = magnesiumPercent;
        this.basesPercent = basesPercent;
   
    }

    public Saturation() {
      
        this.aluminumPercent = 0.0;
        this.calciumPercent = 0.0;
        this.potassiumPercent = 0.0;
        this.magnesiumPercent = 0.0;
        this.basesPercent = 0.0;
   
    }

}
