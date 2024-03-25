package com.example.sistlabsolos.abstracts;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Client;

@Repository
public abstract class ClientAbstract implements IDAO<Client> {
    public abstract List<Client> getClientsByNameDesc();
    public abstract List<Client> getClientsByNameAsc();
    public abstract List<Client> getClientsByCityAsc();
    public abstract List<Client> getClientsByCityDesc();
    public abstract List<Client> getClientsByEmailAsc();
    public abstract List<Client> getClientsByEmailDesc();
    public abstract List<Client> getClientsByEmailSearch(String name);
    public abstract List<Client> getClientsByNameSearch(String name);
    public abstract List<Client> getClientsByCitySearch(String city);
    public abstract Optional<Client> getClientById(UUID clientId);
    public abstract Client getClientByName(String name);
}
