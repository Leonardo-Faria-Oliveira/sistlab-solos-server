package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Role;

@Repository
public abstract class RoleAbstract {
    public abstract Role create(String name);
    public abstract List<Role> getRoles();
    public abstract Optional<Role> getRoleById(UUID roleId);
    public abstract Role getRoleByName(String name);
}
