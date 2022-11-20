package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.entity.ExamQuestions;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExamsQuestionsBO {
    boolean saveExam(ExamQuestionsDTO examsDTO) throws SQLException, Exception;
    ExamQuestionsDTO getQuestion(String exmID, int qNo) throws SQLException, ClassNotFoundException;

    int getQuestionCount() throws SQLException, ClassNotFoundException;
}
