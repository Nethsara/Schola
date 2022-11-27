package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.ParentDAO;
import me.siyum.schola.entity.Parent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParentDAOImpl implements ParentDAO {
    @Override
    public boolean save(Parent parent) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO parents VALUES(?,?,?,?,?,?)",
                parent.getId(),
                parent.getName(),
                parent.getEmail(),
                parent.getNic(),
                parent.getAddress(),
                parent.getPhone()
        );
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM parents ORDER BY pID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Parent parent) {
        return false;
    }

    @Override
    public boolean delete(String integer) {
        return false;
    }

    @Override
    public ArrayList<Parent> search(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM parents");

        ArrayList<Parent> list = new ArrayList<>();
        while (res.next()) {
            list.add(new Parent(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
            ));
        }
        return list;
    }


    @Override
    public Parent retrieve(String id) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM parents WHERE pID = ?", id);
        if (res.next()) {
            return new Parent(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6)
            );
        }
        return null;
    }


    @Override
    public String getID(String s) {
        return "";
    }


    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }

}
