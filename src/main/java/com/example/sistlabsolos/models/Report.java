package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Report implements Serializable {
 
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reportId;

    @NotBlank
    private String landName;

    @NotBlank
    private String city;

    @NotBlank
    private String field;

    @NotNull
    private Double depth;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Report(UUID reportId, @NotBlank String landName, @NotBlank String field, @NotNull Double depth,
            LocalDateTime createdAt) {
        this.reportId = reportId;
        this.landName = landName;
        this.field = field;
        this.depth = depth;
        this.createdAt = createdAt;
    }

    public Report(@NotBlank String landName, @NotBlank String field, @NotNull Double depth,
    LocalDateTime createdAt,String city) {

        this.landName = landName;
        this.field = field;
        this.city = city;
        this.depth = depth;
        this.createdAt = createdAt;
        
    }
    
}
