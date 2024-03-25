package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "laboratorios",
uniqueConstraints = @UniqueConstraint(columnNames={"email"}),
indexes = @Index(columnList = "name"))
public class Lab implements Serializable{
    
    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID labId;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private String markUrl;

    private String contact;

    private String header1;

    private String header2;

    private String header3;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lab", fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lab", fetch = FetchType.EAGER)
    private List<PhosphorValue> phosphorValueList;

    @OneToMany(mappedBy = "lab", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<Subscription> subscriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "lab", fetch = FetchType.EAGER, orphanRemoval = false)
    private List<Employee> employeeList = new ArrayList<>();

    @OneToMany(mappedBy = "lab", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Client> clientList = new ArrayList<>();

    @OneToMany(mappedBy = "lab", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<ChemicalPhysicalReport> chemicalPhysicalReportList = new ArrayList<>();

    public Lab(
        @NotBlank String name, 
        @NotBlank @UniqueElements String email, 
        String markUrl, 
        String contact,
        LocalDateTime createdAt, 
        boolean active, 
        @NotBlank Address address
    ) {

        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;

    }

    public Lab(
        @NotBlank String name, 
        @NotBlank @UniqueElements String email, 
        String markUrl, 
        String contact,
        LocalDateTime createdAt, 
        boolean active, 
        @NotBlank Address address,
        Subscription sub,
        Employee emp
    ) {

        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;
        this.subscriptionList.add(sub);
        this.employeeList.add(emp);

    }

    public Lab(
        UUID labId, 
        @NotBlank String name, 
        @NotBlank @UniqueElements String email, 
        String markUrl,
        String contact, 
        LocalDateTime createdAt, 
        boolean active, 
        @NotBlank Address address
    ) {

        this.labId = labId;
        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;

    }

    public Lab(
        UUID labId,
        @NotBlank String name,
        @NotBlank @UniqueElements String email, 
        String markUrl,
        String contact, 
        LocalDateTime createdAt, 
        boolean active, 
        @NotBlank Address address,
        String header1,
        String header2,
        String header3
    ) {

        this.labId = labId;
        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;
        this.header1 = header1;
        this.header2 = header2;
        this.header3 = header3;

    }

    public Lab() {

    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
