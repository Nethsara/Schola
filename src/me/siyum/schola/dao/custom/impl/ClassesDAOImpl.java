package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.ClassesDAO;
import me.siyum.schola.entity.Classes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassesDAOImpl implements ClassesDAO {
    @Override
    public boolean save(Classes classes) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO class VALUES(?,?,?,?,?,?,?)",
                classes.getId(),
                classes.getSubID(),
                classes.getLecturer(),
                classes.getClssRoom(),
                classes.getBatch(),
                classes.getDate(),
                classes.getTime());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM class ORDER BY cID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Classes classes) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Classes> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Classes> classList = new ArrayList<>();

        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM class");
        while (res.next()) {
            classList.add(
                    new Classes(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getString(5),
                            res.getDate(6).toLocalDate(),
                            res.getTime(7).toLocalTime()
                    )
            );
        }
        return classList;
    }

    @Override
    public Classes retrieve(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM class WHERE cID=?", s);
        if (res.next()) {
            return new Classes(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getDate(6).toLocalDate(),
                    res.getTime(7).toLocalTime()
            );
        }
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

}
