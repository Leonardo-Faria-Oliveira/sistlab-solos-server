package com.example.sistlabsolos.abstracts;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.models.Admin;
import com.example.sistlabsolos.models.Institution;
import com.example.sistlabsolos.models.Role;


@Repository
public abstract class AdminAbstract {
    public abstract Admin create(
        String name, 
        String email,
        String password,
        String contact,
        LocalDateTime createdAt,
        boolean active,
        Role role,
        Institution institution
    );
    public abstract List<Admin> getAdmins();
    public abstract Optional<Admin> getAdminById(UUID adminId);
    public abstract Optional<Admin> getAdminByEmailAndPassword(String email, String password) throws Exception;
}
