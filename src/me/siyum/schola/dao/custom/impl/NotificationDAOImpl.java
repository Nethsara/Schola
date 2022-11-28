package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.custom.NotificationDAO;
import me.siyum.schola.entity.Notification;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationDAOImpl implements NotificationDAO {
    @Override
    public boolean save(Notification notification) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Notification notification) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Notification> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Notification retrieve(String s) throws SQLException, ClassNotFoundException {
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
