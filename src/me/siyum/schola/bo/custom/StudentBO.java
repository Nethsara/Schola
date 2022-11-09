package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.StudentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface StudentBO {

    boolean saveStudent(StudentDTO student) throws SQLException, ClassNotFoundException;
    ResultSet retrieveStudent(int id);
    int getLastID() throws SQLException, ClassNotFoundException;
}
