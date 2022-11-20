package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.ExamsBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.ExamsQuestionsDAO;
import me.siyum.schola.dto.ExamQuestionsDTO;
import me.siyum.schola.dto.ExamsDTO;
import me.siyum.schola.entity.ExamQuestions;

import java.sql.SQLException;

public class ExamsBoImpl implements ExamsBO {
    ExamsQuestionsDAO examsQDAO = DAOFactory.getInstance().getDAO(DAOTypes.EXAM_QUESTIONS);

    public boolean saveExamQuestion(ExamQuestionsDTO exam) throws Exception {
        return examsQDAO.save(new ExamQuestions(
                exam.getQuestionNo(),
                exam.getExmID(),
                exam.getQuestion(),
                exam.getMcq1(),
                exam.getMcq2(),
                exam.getMcq3(),
                exam.getMcq4(),
                exam.getCorrectAns()
        ));

    }


    @Override
    public boolean saveExams(ExamsDTO examsDTO) throws SQLException, Exception {
        return false;
    }
}
