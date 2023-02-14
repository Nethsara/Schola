package me.siyum.schola.bo.custom;

import javafx.collections.ObservableList;
import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.view.lecturers.tm.LecturerAttendanceMarkTM;
import me.siyum.schola.view.receptionist.tm.ReceptionistAttendanceMarkTM;

import java.sql.SQLException;

public interface AttendanceMarkingBO extends SuperBO {
    boolean markAttendance(ObservableList<LecturerAttendanceMarkTM> st, String attendanceID, String classID, int totalSt) throws SQLException;

    boolean markAttendanceReceptionist(ObservableList<ReceptionistAttendanceMarkTM> st, String attendanceID, String classID, int totalSt) throws SQLException, ClassNotFoundException;
}
