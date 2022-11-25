package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.StudentMarks;

import java.sql.SQLException;

public interface StudentMarkDAO extends CrudDAO<StudentMarks, String> {
    double getExmMarkByIDs(String stID, String exmID) throws SQLException, ClassNotFoundException;

}
