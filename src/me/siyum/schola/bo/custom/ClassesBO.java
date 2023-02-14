package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.ClassesDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ClassesBO extends SuperBO {
    ArrayList<ClassesDTO> getAllClasses(String s) throws SQLException, ClassNotFoundException;

    boolean scheduleClass(ClassesDTO classesDTO) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;

    ClassesDTO getClassByID(String id) throws SQLException, ClassNotFoundException;
}
