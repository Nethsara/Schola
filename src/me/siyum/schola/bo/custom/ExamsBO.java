package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.ExamsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamsBO extends SuperBO {
    boolean saveExams(ExamsDTO examsDTO) throws Exception;

    ArrayList<ExamsDTO> getAllExams() throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;
}
