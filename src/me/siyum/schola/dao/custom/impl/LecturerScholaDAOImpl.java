package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.custom.LecturerScholaDAO;
import me.siyum.schola.entity.LecturerSchola;

import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerScholaDAOImpl implements LecturerScholaDAO {
    @Override
    public boolean save(LecturerSchola lecturerSchola) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(LecturerSchola lecturerSchola) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<LecturerSchola> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public LecturerSchola retrieve(String s) throws SQLException, ClassNotFoundException {
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
