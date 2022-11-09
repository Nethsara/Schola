package me.siyum.schola.dao;

import me.siyum.schola.entity.SuperEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CrudDAO<T extends SuperEntity,ID> {
    boolean save(T t) throws SQLException, ClassNotFoundException;
    int getLastID() throws SQLException, ClassNotFoundException;
    boolean update(T t);
    boolean delete(ID id);
    ResultSet retrieve(ID id) throws SQLException, ClassNotFoundException;
    ResultSet retrieve() throws SQLException, ClassNotFoundException;
}
