package com.example.sistlabsolos.interfaces;

import java.sql.Date;
import java.util.UUID;

public interface IAccountInterface {
    UUID id =  new UUID(0, 0);
    String name = "";
    String email = "";
    String password = "";
    String contact = "";
    Date createdAt = new Date(0);
    boolean active = true;
  
}
