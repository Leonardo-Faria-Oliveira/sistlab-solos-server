package com.example.sistlabsolos.abstracts;

import java.util.Optional;
import java.util.UUID;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Scale;

public abstract class ScaleAbstract implements IDAO<Scale> {

    public abstract Optional<Scale> getScaleById(UUID scaleId);
    public abstract Optional<Scale> getScaleByPropertyName(String propertyName);
   
}
