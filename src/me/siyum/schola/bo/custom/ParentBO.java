package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ParentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ParentBO {
    int getLastID() throws SQLException, ClassNotFoundException;
    boolean saveParent(ParentDTO parentDTO) throws SQLException, ClassNotFoundException;
    ResultSet retrieve(int id) throws SQLException, ClassNotFoundException;
    ResultSet retrieve() throws SQLException, ClassNotFoundException;

}
