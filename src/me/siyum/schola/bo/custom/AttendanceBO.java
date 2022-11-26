package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.AttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceBO {
    boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException, ClassNotFoundException;

    ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException;

    AttendanceDTO getAttendanceByID(String id) throws SQLException, ClassNotFoundException;
}
