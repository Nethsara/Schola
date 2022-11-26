package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.Subjects;

import java.sql.SQLException;

public interface SubjectsDAO extends CrudDAO<Subjects, String> {
    String getLecturerByID(String id) throws SQLException, ClassNotFoundException;
}
