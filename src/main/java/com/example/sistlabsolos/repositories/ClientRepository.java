package com.example.sistlabsolos.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.models.Client;
import com.example.sistlabsolos.models.Lab;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    public List<Client> findByOrderByCreatedAtDesc();
    public List<Client> findByLabOrderByCreatedAtDesc(Lab lab);
    public List<Client> findByLabOrderByNameDesc(Lab lab);
    public List<Client> findByLabOrderByNameAsc(Lab lab);
    public List<Client> findByLabOrderByCityDesc(Lab lab);
    public List<Client> findByLabOrderByCityAsc(Lab lab);
    public List<Client> findByLabOrderByEmailDesc(Lab lab);
    public List<Client> findByLabOrderByEmailAsc(Lab lab);
    public List<Client> findTop3ByLabAndEmailContainingIgnoreCase(Lab lab, String email);
    public List<Client> findTop3ByLabAndNameContainingIgnoreCase(Lab lab, String name);
    public List<Client> findTop3ByLabAndCityContainingIgnoreCase(Lab lab, String city);
    Client findByName(String name);
    Client findByEmail(String email);

}
