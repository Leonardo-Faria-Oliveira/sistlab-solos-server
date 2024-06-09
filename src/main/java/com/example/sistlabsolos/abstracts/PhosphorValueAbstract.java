package com.example.sistlabsolos.abstracts;


import org.springframework.stereotype.Repository;
import com.example.sistlabsolos.interfaces.IDAO;
import com.example.sistlabsolos.models.Lab;
import com.example.sistlabsolos.models.PhosphorValue;

@Repository
public abstract class PhosphorValueAbstract implements IDAO<PhosphorValue> {

    public abstract PhosphorValue getLastPhosphorValue(Lab lab);
    public abstract Double calculatePhosphorAbsorbance(PhosphorValue phosphorValue);
   
}
