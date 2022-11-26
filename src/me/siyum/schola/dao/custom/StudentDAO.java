package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student, String> {
    ArrayList<Student> search(boolean b) throws SQLException, ClassNotFoundException;

    ArrayList<Student> filter(String filter) throws SQLException, ClassNotFoundException;

    ArrayList<Student> filterByMarks(String filter, String exm) throws SQLException, ClassNotFoundException;
}
