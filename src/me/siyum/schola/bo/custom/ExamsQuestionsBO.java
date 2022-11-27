package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ExamQuestionsDTO;

import java.sql.SQLException;

public interface ExamsQuestionsBO {
    boolean saveExamQuestion(ExamQuestionsDTO examsDTO) throws SQLException, Exception;

    ExamQuestionsDTO getQuestion(String exmID, int qNo) throws SQLException, ClassNotFoundException;

    int getQuestionCount(String id) throws SQLException, ClassNotFoundException;
}
