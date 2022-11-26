package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.AttendanceMarkDAO;
import me.siyum.schola.entity.AttendanceMark;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceMarkDAOImpl implements AttendanceMarkDAO {


    @Override
    public boolean save(AttendanceMark attendanceMark) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO attendance_student VALUES(?,?,?)",
                attendanceMark.getAmID(),
                attendanceMark.getStID(),
                attendanceMark.isPresent());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(AttendanceMark attendanceMark) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<AttendanceMark> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public AttendanceMark retrieve(String s) throws SQLException, ClassNotFoundException {
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
