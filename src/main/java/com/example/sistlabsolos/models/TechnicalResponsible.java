package com.example.sistlabsolos.models;

import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "responsaveis_tecnicos")
public class TechnicalResponsible extends Employee {

    public String CREA;

    public TechnicalResponsible() {
        super();
    }

    public TechnicalResponsible(@NotBlank String name, @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, String contact, LocalDateTime createdAt, boolean active,
        String job, Role role, Lab lab, String crea) {
        super(name, email, password, contact, createdAt, active, job, role, lab);
        CREA = crea;
    }

    public TechnicalResponsible(UUID id, @NotBlank String name, @NotBlank @UniqueElements String email,
            @NotBlank @UniqueElements String password, String contact, LocalDateTime createdAt, boolean active,
            String job, Role role, Lab lab, String crea) {
        super(id, name, email, password, contact, createdAt, active, job, role, lab);
        CREA = crea;
    }

    public String getCREA() {
        return CREA;
    }

    public void setCREA(String cREA) {
        CREA = cREA;
    }
    
    
    
}
