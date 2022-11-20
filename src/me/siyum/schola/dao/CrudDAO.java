package me.siyum.schola.dao;

import me.siyum.schola.entity.SuperEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity,ID> {
    boolean save(T t) throws SQLException, ClassNotFoundException;
    String getLastID() throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    boolean delete(ID id) throws SQLException, ClassNotFoundException;
    ArrayList<T> search(ID s) throws SQLException, ClassNotFoundException;
    ResultSet retrieve(ID id) throws SQLException, ClassNotFoundException;
    ResultSet retrieve() throws SQLException, ClassNotFoundException;
    String getID(ID s) throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
