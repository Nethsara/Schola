package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.UsersDAO;
import me.siyum.schola.entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAOImpl implements UsersDAO {
    @Override
    public boolean save(Users users) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO users VALUES(?,?,?,?,?)",
                users.geteID(),
                users.getUserName(),
                users.getPassword(),
                users.getRole(),
                users.geteID());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT uID FROM users ORDER BY -substring_index(uID,'-',1) LIMIT 1");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Users users) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Users> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Users retrieve(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM users WHERE uID=?", s);
        if (res.next()) {
            return new Users(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5)
            );
        }
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String getIDByToken(String s, String role) throws SQLException, ClassNotFoundException {
        return null;
    }
}
