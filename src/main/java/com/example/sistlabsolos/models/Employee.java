package com.example.sistlabsolos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.validator.constraints.UniqueElements;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "funcionarios",
    uniqueConstraints = @UniqueConstraint(columnNames={"crea"})
)
public class Employee extends Account implements Serializable{

    @NotBlank
    private String job;
  
    private String crea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role.roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lab.labId")
    private Lab lab;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Employee_Reports> reportsList = new ArrayList<>();

    public Employee(
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, 
        String contact, 
        LocalDateTime createdAt, 
        boolean active,
        @NotBlank String job, 
        Role role, 
        Lab lab
    ) {

        super(name, email, password, contact, createdAt, active);
        this.job = job;
        this.role = role;
        this.lab = lab;
    
    }

    public Employee(
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, 
        String contact, 
        LocalDateTime createdAt, 
        boolean active,
        @NotBlank String job, 
        String crea, 
        Role role, 
        Lab lab
    ) {
                
        super(name, email, password, contact, createdAt, active);
        this.job = job;
        this.crea = crea;
        this.role = role;
        this.lab = lab;

    }

    public Employee() {

        super(
            "", 
            "", 
            "", 
            "", 
            LocalDateTime.now(), 
            true
        );
        this.job = "";
        this.role = new Role();
        this.lab = new Lab();
        this.reportsList.add(null);

    }

    public Employee(
        UUID id, 
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, 
        String contact, 
        LocalDateTime createdAt, 
        boolean active,
        String job,
        Role role,
        Lab lab
    ) {

        super(id, name, email, password, contact, createdAt, active);
        this.job = job;
        this.role = role;
        this.lab = lab;

    }

    public Employee(
        UUID id, 
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        String contact, 
        LocalDateTime createdAt, 
        boolean active,
        String job,
        Role role,
        Lab lab
    ) {

        super(
            id, 
            name, 
            email, 
            contact, 
            createdAt, 
            active
        );
        this.job = job;
        this.role = role;
        this.lab = lab;

    }

}
