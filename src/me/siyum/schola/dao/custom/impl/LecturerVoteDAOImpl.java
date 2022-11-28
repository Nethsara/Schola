package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.LecturerVoteDAO;
import me.siyum.schola.entity.LecturerVote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LecturerVoteDAOImpl implements LecturerVoteDAO {
    @Override
    public boolean save(LecturerVote lecturerVote) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO lecturer_vote VALUES(?,?)",
                lecturerVote.getLecturer(), lecturerVote.getVote());
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
        ArrayList<LecturerVote> list = new ArrayList<>();
        ResultSet res = CRUDUtil.execute("SELECT * FROM lecturer_vote WHERE lecturer=?", s);
        while (res.next()) {
            list.add(
                    new LecturerVote(
                            res.getString(1),
                            res.getInt(2)
                    )
            );
        }
        return list;
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
