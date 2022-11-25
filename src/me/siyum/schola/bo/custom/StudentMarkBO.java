package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.StudentMarkDTO;

import java.sql.SQLException;

public interface StudentMarkBO {
    double getMarkByID(String stID, String exmID) throws SQLException, ClassNotFoundException;

    boolean saveExmStMarks(StudentMarkDTO stMarkDTO) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;
}
