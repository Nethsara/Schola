package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ExamsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ExamsBO {
    boolean saveExams(ExamsDTO examsDTO) throws SQLException, Exception;

    ArrayList<ExamsDTO> getAllExams() throws SQLException, ClassNotFoundException;
}
