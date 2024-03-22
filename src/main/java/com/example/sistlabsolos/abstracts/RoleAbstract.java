package com.example.sistlabsolos.abstracts;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Role;

@Repository
public abstract class RoleAbstract implements IDAO<Role>{
    public abstract Optional<Role> getRoleById(UUID roleId);
    public abstract Role getRoleByName(String name);
}
