package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.ExamsQuestionsDAO;
import me.siyum.schola.entity.ExamQuestions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamQuestionsDAOImpl implements ExamsQuestionsDAO {

    @Override
    public boolean save(ExamQuestions examQuestions) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO exam_questions VALUES(?,?,?,?,?,?,?,?)",
                examQuestions.getQuestionNo(),
                examQuestions.getExmID(),
                examQuestions.getQuestion(),
                examQuestions.getMcq1(),
                examQuestions.getMcq2(),
                examQuestions.getMcq3(),
                examQuestions.getMcq4(),
                examQuestions.getCorrectAns()
        );
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ExamQuestions examQuestions) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<ExamQuestions> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<ExamQuestions> exmQ = new ArrayList<>();

        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM exam_questions WHERE qID LIKE ? || exmID LIKE ? ",
                s, s);
        while (res.next()) {
            exmQ.add(
                    new ExamQuestions(
                            res.getInt(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getString(6),
                            res.getString(7),
                            res.getInt(8)
                    )
            );
        }
        return exmQ;
    }

    @Override
    public ExamQuestions retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT COUNT(qID) FROM exam_questions");
        if (res.next()) {
            return res.getInt(1);
        }
        return 0;
    }

    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }

    @Override
    public ExamQuestions retrieve(String exmID, int qNO) throws SQLException, ClassNotFoundException {

        ResultSet res = CRUDUtil.execute("SELECT * FROM exam_questions WHERE qID=? && exmID=?",
                qNO, exmID
        );
        if (res.next()) {
            return new ExamQuestions(
                    res.getInt(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(7),
                    res.getInt(8)
            );
        }
        return null;
    }
}
