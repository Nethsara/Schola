package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.HomeWorkStudentDAO;
import me.siyum.schola.entity.HomeWorkStudents;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeWorkStudentDAOImpl implements HomeWorkStudentDAO {
    @Override
    public boolean save(HomeWorkStudents homeWorkStudents) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO homework_students VALUES(?,?,?,?,?,?)",
                homeWorkStudents.getId(), homeWorkStudents.getStID(), homeWorkStudents.getName(),
                homeWorkStudents.getFile(), homeWorkStudents.getDateSubmitted(),
                homeWorkStudents.isStatus());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(HomeWorkStudents homeWorkStudents) throws SQLException, ClassNotFoundException {
        System.out.println("Updaing");
        return CRUDUtil.execute("UPDATE homework_students SET stID=?,stName=?,file=?,date=?,status=? WHERE hwsID=?",
                homeWorkStudents.getStID(), homeWorkStudents.getName(),
                homeWorkStudents.getFile(), homeWorkStudents.getDateSubmitted(),
                homeWorkStudents.isStatus(), homeWorkStudents.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<HomeWorkStudents> search(String s) throws SQLException, ClassNotFoundException {
        System.out.println(s + " dao search");
        ArrayList<HomeWorkStudents> list = new ArrayList<>();
        ResultSet res = CRUDUtil.execute("SELECT * FROM homework_students WHERE hwsID=?", s);
        while (res.next()) {
            list.add(
                    new HomeWorkStudents(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getDate(5).toLocalDate(),
                            res.getBoolean(6),
                            res.getBlob(4)
                    )
            );
        }
        return list;
    }

    @Override
    public HomeWorkStudents retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getIDByToken(String s, String role) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public HomeWorkStudents retrieve(String st, String ex) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM homework_students WHERE hwsID=? && stID=?", ex, st);
        if (res.next()) {
            return new HomeWorkStudents(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getDate(5).toLocalDate(),
                    res.getBoolean(6),
                    res.getBlob(4)

            );
        }
        return null;
    }
}
