package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "equacao_fosforo")
public class PhosphorValue implements Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private Double x1;

    @NotNull
    private Double x2;

    @NotNull
    private Double x3;

    @NotNull
    private Double x4;

    @NotNull
    private Double x5;

    private Double y1;

    private Double y2;

    private Double y3;

    private Double y4;

    private Double y5;

    private Double absorbanceValue;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "phosphorValue", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<ChemicalPhysicalReport> chemicalPhysicalReportsList;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;

    public PhosphorValue(UUID id, @NotBlank Double x1, @NotBlank Double x2, @NotNull Double x3, @NotNull Double x4,
            @NotNull Double x5, @NotBlank Double y1, @NotBlank Double y2, @NotNull Double y3, @NotNull Double y4,
            @NotNull Double y5, @NotNull Double absorbanceValue, LocalDateTime createdAt, Lab lab) {
        this.id = id;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.lab = lab;
        this.absorbanceValue = absorbanceValue;
        this.createdAt = createdAt;
    }

    public PhosphorValue(@NotBlank Double x1, @NotBlank Double x2, @NotNull Double x3, @NotNull Double x4,
    @NotNull Double x5, @NotBlank Double y1, @NotBlank Double y2, @NotNull Double y3, @NotNull Double y4,
    @NotNull Double y5, LocalDateTime createdAt, Lab lab, @NotNull Double absorbanceValue) {
    
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.lab = lab;
        this.absorbanceValue = absorbanceValue;
        this.createdAt = createdAt;

    }

    public PhosphorValue(@NotBlank Double x1, @NotBlank Double x2, @NotNull Double x3, @NotNull Double x4,
    @NotNull Double x5, LocalDateTime createdAt) {
    
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.x4 = x4;
        this.x5 = x5;
        this.createdAt = createdAt;

    }

    public PhosphorValue(UUID id, @NotBlank Double y1, @NotBlank Double y2, @NotNull Double y3, @NotNull Double y4,
    @NotNull Double y5, @NotNull Double absorbanceValue, LocalDateTime createdAt) {
        
        this.id = id;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.y4 = y4;
        this.y5 = y5;
        this.absorbanceValue = absorbanceValue;

    }

    public PhosphorValue() {
    
        this.x1 = 0.0;
        this.x2 = 0.0;
        this.x3 = 0.0;
        this.x4 = 0.0;
        this.x5 = 0.0;
        this.y1 = 0.0;
        this.y2 = 0.0;
        this.y3 = 0.0;
        this.y4 = 0.0;
        this.y5 = 0.0;
        this.lab = new Lab();
        this.absorbanceValue = 0.0;

    }
 

}
