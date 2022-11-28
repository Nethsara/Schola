package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.custom.NotificationStudentDAO;
import me.siyum.schola.entity.NotificationStudents;

import java.sql.SQLException;
import java.util.ArrayList;

public class NotificationStudentDAOImpl implements NotificationStudentDAO {
    @Override
    public boolean save(NotificationStudents notificationStudents) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(NotificationStudents notificationStudents) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<NotificationStudents> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public NotificationStudents retrieve(String s) throws SQLException, ClassNotFoundException {
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
