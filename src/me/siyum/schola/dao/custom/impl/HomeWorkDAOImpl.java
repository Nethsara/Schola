package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.HomeWorkDAO;
import me.siyum.schola.entity.HomeWorks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeWorkDAOImpl implements HomeWorkDAO {
    @Override
    public boolean save(HomeWorks homeWorks) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO homeworks VALUES(?,?,?,?,?,?)",
                homeWorks.getId(),
                homeWorks.getLecturerID(),
                homeWorks.getCreateDate(),
                homeWorks.getSubmissionDate(),
                homeWorks.getBatch(),
                homeWorks.getMessage());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(HomeWorks homeWorks) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<HomeWorks> search(String s) throws SQLException, ClassNotFoundException {
        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM homeworks WHERE hwID LIKE ? || lecturer LIKE ?", s, s);
        ArrayList<HomeWorks> list = new ArrayList<>();
        while (res.next()) {
            list.add(
                    new HomeWorks(
                            res.getString(1),
                            res.getString(2),
                            res.getDate(3).toLocalDate(),
                            res.getDate(4).toLocalDate(),
                            res.getString(5),
                            res.getString(6)

                    )
            );
        }
        return list;
    }

    @Override
    public HomeWorks retrieve(String s) throws SQLException, ClassNotFoundException {
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
