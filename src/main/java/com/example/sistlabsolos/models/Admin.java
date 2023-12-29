package com.example.sistlabsolos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "administradores", 
uniqueConstraints = @UniqueConstraint(columnNames={"name", "email", "contact"}),
indexes = @Index(columnList = "name"))
public class Admin extends Account implements Serializable {
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role.roleId")
    private Role role;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution.institutionId")
    private Institution institution;

    public Admin(UUID id, 
    @NotBlank String name,
    @NotBlank @UniqueElements String email, 
    @NotBlank @UniqueElements String password, 
    String contact, 
    LocalDateTime createdAt, 
    boolean active,
    Role role, 
    Institution institution) {
        super(id, name, email, password, contact, createdAt, active);
        this.role = role;
        this.institution = institution;
    }

    public Admin(UUID id, 
    @NotBlank String name,
    @NotBlank @UniqueElements String email, 
    String contact, 
    LocalDateTime createdAt, 
    boolean active,
    Role role, 
    Institution institution) {
        super(id, name, email, contact, createdAt, active);
        this.role = role;
        this.institution = institution;
    }

    public Admin(@NotBlank String name,
    @NotBlank @UniqueElements String email, 
    @NotBlank @UniqueElements String password, 
    String contact, 
    LocalDateTime createdAt, 
    boolean active,
    Role role, 
    Institution institution) {
        super(name, email, password, contact, createdAt, active);
        this.role = role;
        this.institution = institution;
    }

    public Admin() {
        super("", "", "", "", LocalDateTime.now(), true);
        this.role = new Role();
        this.institution = new Institution();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    
}
