package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ExamsDTO;

import java.sql.SQLException;

public interface ExamsBO {
    boolean saveExams(ExamsDTO examsDTO) throws SQLException, Exception;
}