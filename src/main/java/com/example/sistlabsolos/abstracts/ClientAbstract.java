package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class ClientAbstract implements IDAO<Client> {
    public abstract List<Client> getClientsByNameDesc(Lab lab);
    public abstract List<Client> getClientsByNameAsc(Lab lab);
    public abstract List<Client> getClientsByCityAsc(Lab lab);
    public abstract List<Client> getClientsByCityDesc(Lab lab);
    public abstract List<Client> getClientsByEmailAsc(Lab lab);
    public abstract List<Client> getClientsByEmailDesc(Lab lab);
    public abstract List<Client> getClientsByEmailSearch(Lab lab, String name);
    public abstract List<Client> getClientsByNameSearch(Lab lab, String name);
    public abstract List<Client> getClientsByCitySearch(Lab lab, String city);
    public abstract Optional<Client> getClientById(UUID clientId);
    public abstract Client getClientByName(String name);
    public abstract List<Client> getClientByLab(Lab lab);
}
