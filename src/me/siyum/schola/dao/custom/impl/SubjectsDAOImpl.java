package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.SubjectsDAO;
import me.siyum.schola.entity.Subjects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubjectsDAOImpl implements SubjectsDAO {
    @Override
    public boolean save(Subjects subjects) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Subjects subjects) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Subjects> search(String s) throws SQLException, ClassNotFoundException {
        System.out.println("");
        s = "%" + s + "%";
        ArrayList<Subjects> arList = new ArrayList<>();
        ResultSet rs = CRUDUtil.execute("SELECT * FROM subjects WHERE subID LIKE ? || name LIKE ? || lecturer LIKE ? ",
                s, s, s);

        while (rs.next()) {
            arList.add(
                    new Subjects(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3)
                    )
            );
        }

        return arList;
    }

    @Override
    public Subjects retrieve(String s) throws SQLException, ClassNotFoundException {
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
    public String getLecturerByID(String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = CRUDUtil.execute("SELECT lecturer FROM subjects WHERE subID=?", id);
        if (rs.next()) {
            return rs.getString(1);
        } else {
            return "";
        }
    }
}
