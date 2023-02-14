package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.ClassesDTO;

import java.sql.SQLException;

public interface ClassSchedulerBO extends SuperBO {
    boolean scheduleClass(ClassesDTO classObject, AttendanceDTO attendanceDTO) throws SQLException;
}
