package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    public List<Client> findByOrderByCreatedAtDesc();
    public List<Client> findByOrderByNameDesc();
    public List<Client> findByOrderByNameAsc();
    public List<Client> findByOrderByCityDesc();
    public List<Client> findByOrderByCityAsc();
    public List<Client> findByOrderByEmailDesc();
    public List<Client> findByOrderByEmailAsc();
    public List<Client> findTop3ByEmailContainingIgnoreCase(String email);
    public List<Client> findTop3ByNameContainingIgnoreCase(String name);
    public List<Client> findTop3ByCityContainingIgnoreCase(String city);
    Client findByName(String name);
    Client findByEmail(String email);

}
