package com.example.sistlabsolos.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "funcionarios_laudos")
public class Employee_Reports implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee.id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chemicalPhysicalReport.reportId")
    private ChemicalPhysicalReport chemicalPhysicalReport;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    public Employee_Reports(Employee employee, ChemicalPhysicalReport chemicalPhysicalReport) {
        this.employee = employee;
        this.chemicalPhysicalReport = chemicalPhysicalReport;
    }


    
    
}
