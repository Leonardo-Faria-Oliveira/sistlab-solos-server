package com.example.sistlabsolos.abstracts;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Admin;

@Repository
public abstract class AdminAbstract implements IDAO<Admin> {
    public abstract Optional<Admin> getAdminById(UUID adminId);
    public abstract Optional<Admin> getAdminByEmail(String email);
}
