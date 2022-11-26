package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.AttendanceDAO;
import me.siyum.schola.entity.Attendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public boolean save(Attendance attendance) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO attendance VALUES(?,?,?,?,?)",
                attendance.getId(),
                attendance.getClassID(),
                attendance.getDate(),
                attendance.getTotalSt(),
                attendance.isStatus());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM attendance ORDER BY amID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Attendance attendance) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE attendance SET status=false WHERE amID=?",
                attendance.getId());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Attendance> search(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM attendance");
        ArrayList<Attendance> returnList = new ArrayList<>();
        while (res.next()) {
            returnList.add(
                    new Attendance(
                            res.getString(1),
                            res.getString(2),
                            res.getDate(3).toLocalDate(),
                            res.getInt(4),
                            res.getBoolean(5)
                    )
            );
        }
        return returnList;

    }

    @Override
    public Attendance retrieve(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM attendance WHERE amID=?", s);
        if (res.next()) {
            return new Attendance(
                    res.getString(1),
                    res.getString(2),
                    res.getDate(3).toLocalDate(),
                    res.getInt(4),
                    res.getBoolean(5)
            );

        }
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

    @Override
    public String getIDByToken(String s, String role) throws SQLException, ClassNotFoundException {
        return null;
    }
}
