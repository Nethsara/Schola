package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentDAO extends CrudDAO<Student, Integer> {
    ArrayList<Student> search(boolean b) throws SQLException, ClassNotFoundException;
}
