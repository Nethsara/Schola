package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.ExamsDAO;
import me.siyum.schola.entity.Exams;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExamDAOImpl implements ExamsDAO {
    @Override
    public boolean save(Exams exams) throws SQLException, ClassNotFoundException {
        System.out.println("Saving " + exams.getId());
        return CRUDUtil.execute("INSERT INTO exam VALUES(?,?,?,?)",
                exams.getId(),
                exams.getDate(),
                exams.getBatch(),
                exams.getLecturer());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM exam ORDER BY exID DESC");
        if(res.next()){
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Exams exams) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Exams> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Exams> exm = new ArrayList<>();

        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM exam WHERE exID LIKE ? || batch LIKE ? || lecturer LIKE ?",
                s, s, s);
        while (res.next()) {
            exm.add(
                    new Exams(
                            res.getString(1),
                            res.getDate(2).toLocalDate(),
                            res.getString(3),
                            res.getString(4)
                    )
            );
        }
        return exm;
    }

    @Override
    public Exams retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }
}
