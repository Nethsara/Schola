package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {
    boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException, ClassNotFoundException;

    ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException;

    AttendanceDTO getAttendanceByID(String id) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;

    boolean updateAttendance(AttendanceDTO attendanceDTO) throws SQLException, ClassNotFoundException;
}
