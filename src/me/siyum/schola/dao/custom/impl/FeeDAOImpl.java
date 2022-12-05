package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.FeeDAO;
import me.siyum.schola.entity.Fee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeeDAOImpl implements FeeDAO {
    @Override
    public boolean save(Fee fee) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM fee ORDER BY id DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Fee fee) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Fee> search(String s) throws SQLException, ClassNotFoundException {
        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM fee");
        ArrayList<Fee> list = new ArrayList<>();
        while (res.next()) {
            list.add(
                    new Fee(
                            res.getString(1),
                            res.getString(2),
                            res.getDouble(3),
                            res.getDate(4).toLocalDate(),
                            res.getBlob(5)
                    )
            );
        }
        return list;
    }

    @Override
    public Fee retrieve(String s) throws SQLException, ClassNotFoundException {
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
}
