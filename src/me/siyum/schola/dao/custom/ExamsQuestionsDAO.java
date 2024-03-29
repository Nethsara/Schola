package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.ExamQuestions;

import java.sql.SQLException;

public interface ExamsQuestionsDAO extends CrudDAO<ExamQuestions, String> {
    ExamQuestions retrieve(String exmID, int qNo) throws SQLException, ClassNotFoundException;

    int examQuestionCount(String exmID) throws SQLException, ClassNotFoundException;
}
