package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.SubjectsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubjectsBO extends SuperBO {
    ArrayList<SubjectsDTO> getAllSubjects(String s) throws SQLException, ClassNotFoundException;

    String getLecturerBySubID(String s) throws SQLException, ClassNotFoundException;

    String getNameByLecturer(String id) throws SQLException, ClassNotFoundException;
}
