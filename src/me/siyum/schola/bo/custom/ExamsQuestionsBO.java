package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.ExamQuestionsDTO;

import java.sql.SQLException;

public interface ExamsQuestionsBO extends SuperBO {
    boolean saveExamQuestion(ExamQuestionsDTO examsDTO) throws Exception;

    ExamQuestionsDTO getQuestion(String exmID, int qNo) throws SQLException, ClassNotFoundException;

    int getQuestionCount(String id) throws SQLException, ClassNotFoundException;
}
