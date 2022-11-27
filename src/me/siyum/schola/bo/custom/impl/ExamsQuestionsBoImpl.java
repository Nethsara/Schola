package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ExamsQuestionsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.ExamsQuestionsDAO;
import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.entity.ExamQuestions;

import java.sql.SQLException;

public class ExamsQuestionsBoImpl implements ExamsQuestionsBO {
    ExamsQuestionsDAO examsDAO = DAOFactory.getInstance().getDAO(DAOTypes.EXAM_QUESTIONS);


    @Override
    public boolean saveExamQuestion(ExamQuestionsDTO examsDTO) throws SQLException, Exception {
        return examsDAO.save(new ExamQuestions(
                examsDTO.getQuestionNo(),
                examsDTO.getExmID(),
                examsDTO.getQuestion(),
                examsDTO.getMcq1(),
                examsDTO.getMcq2(),
                examsDTO.getMcq3(),
                examsDTO.getMcq4(),
                examsDTO.getCorrectAns()
        ));
    }

    @Override
    public ExamQuestionsDTO getQuestion(String exmID, int qNo) throws SQLException, ClassNotFoundException {
        ExamQuestions retrieve = examsDAO.retrieve(exmID, qNo);
        return new ExamQuestionsDTO(
                retrieve.getQuestionNo(),
                retrieve.getExmID(),
                retrieve.getQuestion(),
                retrieve.getMcq1(),
                retrieve.getMcq2(),
                retrieve.getMcq3(),
                retrieve.getMcq4(),
                retrieve.getCorrectAns()
        );
    }

    @Override
    public int getQuestionCount(String id) throws SQLException, ClassNotFoundException {
        return examsDAO.examQuestionCount(id);
    }
}
