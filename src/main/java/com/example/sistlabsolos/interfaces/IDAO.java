package com.example.sistlabsolos.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface IDAO<T> {
    public T create(T obj) throws SQLException;
    public List<T> list();
    public T update(UUID id, T obj);
}
