package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "instituicoes")
public class Institution implements Serializable{
 
    @Serial 
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID institutionId;

    @NotBlank
    @UniqueElements
    private String name;

    @NotBlank
    @UniqueElements
    private String code;

    @OneToMany(mappedBy = "institution", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Admin> adminList = new ArrayList<>();
    
    public Institution() {
        this.name = "";
        this.code = "";
    }

    public Institution( @UniqueElements String name,
            @UniqueElements String code) {
        this.name = name;
        this.code = code;
    }

    public Institution(UUID institutionId, @UniqueElements String name, @UniqueElements String code) {
        this.institutionId = institutionId;
        this.name = name;
        this.code = code;
    }

    public UUID getIdInstitution() {
        return institutionId;
    }

    public void setIdInstitution(UUID institutionId) {
        this.institutionId = institutionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    

}
