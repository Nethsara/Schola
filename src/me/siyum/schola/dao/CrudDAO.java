package me.siyum.schola.dao;

import me.siyum.schola.entity.SuperEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity,ID> {
    boolean save(T t) throws SQLException, ClassNotFoundException;
    String getLastID() throws SQLException, ClassNotFoundException;
    boolean update(T t) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    ArrayList<T> search(String s) throws SQLException, ClassNotFoundException;
    ResultSet retrieve(String id) throws SQLException, ClassNotFoundException;
    ResultSet retrieve() throws SQLException, ClassNotFoundException;
    int getID(String s) throws SQLException, ClassNotFoundException;
}
