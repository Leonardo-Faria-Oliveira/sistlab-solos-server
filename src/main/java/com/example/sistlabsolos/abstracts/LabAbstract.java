package com.example.sistlabsolos.abstracts;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Headers;
import com.example.sistlabsolos.models.Lab;

@Repository
public abstract class LabAbstract implements IDAO<Lab>{
    public abstract Optional<Lab> getLabById(UUID labId);
    public abstract Lab getLabByName(String name);
    public abstract Lab addReportHeaders(String labName, Headers obj) throws SQLException;
}
