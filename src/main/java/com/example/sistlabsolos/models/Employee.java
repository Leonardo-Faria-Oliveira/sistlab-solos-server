package com.example.sistlabsolos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "funcionarios")
public class Employee extends Account implements Serializable{

    @NotBlank
    private String job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role.roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;

    public Employee(@NotBlank String name, @NotBlank @UniqueElements String email,
            @NotBlank @UniqueElements String password, String contact, LocalDateTime createdAt, boolean active,
            @NotBlank String job, Role role, Lab lab) {
        super(name, email, password, contact, createdAt, active);
        this.job = job;
        this.role = role;
        this.lab = lab;
    }

    public Employee(UUID id, @NotBlank String name, 
    @NotBlank @UniqueElements String email,
    @NotBlank @UniqueElements String password, 
    String contact, 
    LocalDateTime createdAt, 
    boolean active,
    String job,
    Role role,
    Lab lab) {
        super(id, name, email, password, contact, createdAt, active);
        this.job = job;
        this.role = role;
        this.lab = lab;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

}
