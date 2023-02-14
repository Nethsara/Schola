package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.StudentMarkDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentMarkBO extends SuperBO {
    double getMarkByID(String stID, String exmID) throws SQLException, ClassNotFoundException;

    ArrayList<StudentMarkDTO> getMarksByID(String stID) throws SQLException, ClassNotFoundException;

    boolean saveExmStMarks(StudentMarkDTO stMarkDTO) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;
}
