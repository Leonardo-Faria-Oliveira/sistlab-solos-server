package com.example.sistlabsolos.models;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.UniqueElements;
import com.example.sistlabsolos.interfaces.account.IAccount;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class Account implements IAccount {

    @Serial 
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String contact;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private boolean active;

    public Account(
        UUID id, 
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, 
        String contact, 
        LocalDateTime createdAt, 
        boolean active
    ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
    
    }

    public Account(
        UUID id, 
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        String contact, 
        LocalDateTime createdAt, 
        boolean active
    ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
    
    }

    public Account(
        @NotBlank String name, 
        @NotBlank @UniqueElements String email,
        @NotBlank @UniqueElements String password, 
        String contact, 
        LocalDateTime createdAt, 
        boolean active
    ) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.createdAt = createdAt;
        this.active = active;
    
    }

}
