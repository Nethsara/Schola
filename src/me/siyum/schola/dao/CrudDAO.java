package me.siyum.schola.dao;

import me.siyum.schola.entity.SuperEntity;
import me.siyum.schola.entity.Tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity,ID> {
    boolean save(T t) throws SQLException, ClassNotFoundException;
    int getLastID() throws SQLException, ClassNotFoundException;
    boolean update(T t);
    boolean delete(ID id);
    ArrayList<Tasks>search(String s) throws SQLException, ClassNotFoundException;
    ResultSet retrieve(ID id) throws SQLException, ClassNotFoundException;
    ResultSet retrieve() throws SQLException, ClassNotFoundException;
}
