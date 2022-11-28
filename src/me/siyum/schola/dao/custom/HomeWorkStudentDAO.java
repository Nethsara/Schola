package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.HomeWorkStudents;

import java.sql.SQLException;

public interface HomeWorkStudentDAO extends CrudDAO<HomeWorkStudents, String> {
    HomeWorkStudents retrieve(String st, String ex) throws SQLException, ClassNotFoundException;
}
