package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.dto.TasksDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO {

    boolean saveStudent(StudentDTO student) throws SQLException, ClassNotFoundException;
    ResultSet retrieveStudent(int id);
    String getLastID() throws SQLException, ClassNotFoundException;
    ArrayList<StudentDTO> searchStudents(String s) throws SQLException, ClassNotFoundException;

}
