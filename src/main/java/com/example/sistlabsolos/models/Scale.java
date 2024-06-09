package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "escala")
public class Scale implements Serializable {
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String propertyName;

    @NotNull
    private Double higher;

    @NotNull
    private Double high;

    @NotNull
    private Double medium;

    @NotNull
    private Double low;

    @NotNull
    private Double lower;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Scale(UUID id, @NotNull Double higher, @NotNull Double high, @NotNull Double medium, @NotNull Double low,
            @NotNull Double lower, LocalDateTime createdAt) {
        this.id = id;
        this.higher = higher;
        this.high = high;
        this.medium = medium;
        this.low = low;
        this.lower = lower;
        this.createdAt = createdAt;
    }

    public Scale(@NotBlank String propertyName, @NotNull Double higher, @NotNull Double high, @NotNull Double medium, @NotNull Double low,
    @NotNull Double lower, LocalDateTime createdAt) {
        
        this.propertyName = propertyName;
        this.higher = higher;
        this.high = high;
        this.medium = medium;
        this.low = low;
        this.lower = lower;
        this.createdAt = createdAt;
    
    }

    public Scale() {
        
        this.higher = 0.0;
        this.high = 0.0;
        this.medium = 0.0;
        this.low = 0.0;
        this.lower = 0.0;  
    
    }

}
