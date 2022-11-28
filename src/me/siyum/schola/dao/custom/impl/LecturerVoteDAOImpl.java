package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.custom.LecturerVoteDAO;
import me.siyum.schola.entity.LecturerVote;

import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerVoteDAOImpl implements LecturerVoteDAO {
    @Override
    public boolean save(LecturerVote lecturerVote) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(LecturerVote lecturerVote) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<LecturerVote> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public LecturerVote retrieve(String s) throws SQLException, ClassNotFoundException {
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
