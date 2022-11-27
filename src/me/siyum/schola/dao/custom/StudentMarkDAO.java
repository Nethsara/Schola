package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.StudentMarks;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentMarkDAO extends CrudDAO<StudentMarks, String> {
    double getExmMarkByIDs(String stID, String exmID) throws SQLException, ClassNotFoundException;


    ArrayList<StudentMarks> getExamMarksByID(String id) throws SQLException, ClassNotFoundException;
}
