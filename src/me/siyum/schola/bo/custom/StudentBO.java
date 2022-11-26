package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO {

    boolean saveStudent(StudentDTO student) throws SQLException, ClassNotFoundException;

    StudentDTO retrieveStudent(String id) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> searchStudents(String s) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> searchStudents(boolean b) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO student) throws SQLException, ClassNotFoundException;

    String getStudentByToken(String token) throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> filterStudents(String s, String exm) throws SQLException, ClassNotFoundException;


}
