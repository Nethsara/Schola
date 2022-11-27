package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.StudentMarkDAO;
import me.siyum.schola.entity.StudentMarks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentMarksDAOImpl implements StudentMarkDAO {
    @Override
    public boolean save(StudentMarks studentMarks) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO student_exam_marks VALUES(?,?,?,?)",
                studentMarks.getId(),
                studentMarks.getExmID(),
                studentMarks.getsID(),
                studentMarks.getMark());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM student_exam_marks ORDER BY semMID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(StudentMarks studentMarks) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<StudentMarks> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public StudentMarks retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }

    @Override
    public double getExmMarkByIDs(String stID, String exmID) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT mark FROM student_exam_marks WHERE stID=? && examID=?", stID, exmID);
        if (res.next()) {
            return res.getDouble(1);
        }
        return -1;
    }

    @Override
    public ArrayList<StudentMarks> getExamMarksByID(String id) throws SQLException, ClassNotFoundException {
        ArrayList<StudentMarks> returnList = new ArrayList<>();
        ResultSet res = CRUDUtil.execute("SELECT * FROM student_exam_marks WHERE stID=?", id);

        while (res.next()) {
            returnList.add(
                    new StudentMarks(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getInt(4)
                    )
            );
        }
        return returnList;
    }

}
