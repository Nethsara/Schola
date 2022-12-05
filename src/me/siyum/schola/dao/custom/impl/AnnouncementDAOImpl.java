package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.AnnouncementsDAO;
import me.siyum.schola.entity.Announcements;
import me.siyum.schola.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnnouncementDAOImpl implements AnnouncementsDAO {
    @Override
    public boolean save(Announcements announcements) throws SQLException, ClassNotFoundException {
        boolean announcementSaved = CRUDUtil.execute("INSERT INTO announcements VALUES(?,?,?)",
                announcements.getId(), announcements.getMessage(), announcements.getFrom());
        if (announcementSaved) {
            if (announcements.getTo().contains("students")) {
                ArrayList<Student> search = new StudentDAOImpl().search("");
                for (Student s : search
                ) {
                    CRUDUtil.execute("INSERT INTO announcements_students VALUES(?,?,?)",
                            announcements.getId(),
                            s.getId(),
                            true);
                }
                return true;
            } else
                return false;
        }
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM announcements ORDER BY anID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Announcements announcements) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Announcements> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Announcements> announcementsList = new ArrayList<>();
        ResultSet res = CRUDUtil.execute("SELECT * FROM announcements");
        while (res.next()) {
            announcementsList.add(
                    new Announcements(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            "students"
                    )
            );
        }
        return announcementsList;
    }

    @Override
    public Announcements retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }
}
