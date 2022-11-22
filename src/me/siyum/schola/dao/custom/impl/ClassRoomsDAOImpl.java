package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.custom.ClassRoomDAO;
import me.siyum.schola.entity.ClassRooms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClassRoomsDAOImpl implements ClassRoomDAO {
    @Override
    public boolean save(ClassRooms classRooms) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(ClassRooms classRooms) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<ClassRooms> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
