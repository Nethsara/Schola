package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.AttendanceMarkDTO;

import java.sql.SQLException;

public interface AttendanceMarkBO {
    boolean saveAttendanceMarking(AttendanceMarkDTO attendanceMarkDTO) throws SQLException, ClassNotFoundException;
}
