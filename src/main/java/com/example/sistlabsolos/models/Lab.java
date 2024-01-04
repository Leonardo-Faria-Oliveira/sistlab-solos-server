package com.example.sistlabsolos.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Value;

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

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Value("${some.key:true}")
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lab", fetch = FetchType.LAZY)
    private Address address;

    @OneToMany(mappedBy = "lab", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Subscription> subscriptionList = new ArrayList<>();

    @OneToMany(mappedBy = "lab", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @OneToMany(mappedBy = "lab", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Client> clientList = new ArrayList<>();



    public Lab(@NotBlank String name, @NotBlank @UniqueElements String email, String markUrl, String contact,
            LocalDateTime createdAt, boolean active, @NotBlank Address address) {
        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;

    }

    public Lab(UUID labId, @NotBlank String name, @NotBlank @UniqueElements String email, String markUrl,
            String contact, LocalDateTime createdAt, boolean active, @NotBlank Address address) {
        this.labId = labId;
        this.name = name;
        this.email = email;
        this.markUrl = markUrl;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
        this.address = address;
     
    }

    public Lab() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public UUID getLabId() {
        return labId;
    }

    public void setLabId(UUID labId) {
        this.labId = labId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMarkUrl() {
        return markUrl;
    }

    public void setMarkUrl(String markUrl) {
        this.markUrl = markUrl;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }

}
