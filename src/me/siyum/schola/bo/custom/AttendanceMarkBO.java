package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.AttendanceMarkDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AttendanceMarkBO {
    boolean saveAttendanceMarking(AttendanceMarkDTO attendanceMarkDTO) throws SQLException, ClassNotFoundException;

    ArrayList<AttendanceMarkDTO> getAttendanceByID(String id) throws SQLException, ClassNotFoundException;
}
