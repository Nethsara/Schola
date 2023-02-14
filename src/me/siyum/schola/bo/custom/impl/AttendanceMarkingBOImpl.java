package me.siyum.schola.bo.custom.impl;

import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.bo.custom.AttendanceMarkBO;
import me.siyum.schola.bo.custom.AttendanceMarkingBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.AttendanceMarkDTO;
import me.siyum.schola.view.lecturers.tm.LecturerAttendanceMarkTM;
import me.siyum.schola.view.receptionist.tm.ReceptionistAttendanceMarkTM;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class AttendanceMarkingBOImpl implements AttendanceMarkingBO {
    private final AttendanceMarkBO attendanceMarkBO = (AttendanceMarkBO) BOFactory.getInstance().getBO(BOTypes.ATTENDANCE_MARK);
    private final AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    @Override
    public boolean markAttendance(ObservableList<LecturerAttendanceMarkTM> st, String attendanceID, String classID, int totalSt) throws SQLException {
        boolean status = false;
        Connection connection = null;
        for (LecturerAttendanceMarkTM re : st
        ) {
            try {
                connection = DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);
                status = attendanceMarkBO.saveAttendanceMarking(
                        new AttendanceMarkDTO(
                                re.getId(),
                                re.getStID(),
                                re.getActions().isSelected()
                        )
                );
                if (status) {
                    boolean updateAttendance = attendanceBO.updateAttendance(
                            new AttendanceDTO(
                                    attendanceID,
                                    classID,
                                    LocalDate.now(),
                                    totalSt,
                                    false
                            )
                    );
                    if (updateAttendance) {
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION, "Success!").show();
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                assert connection != null;
                connection.setAutoCommit(true);
            }
        }
        return status;
    }

    @Override
    public boolean markAttendanceReceptionist(ObservableList<ReceptionistAttendanceMarkTM> st, String attendanceID, String classID, int totalSt) throws SQLException, ClassNotFoundException {
        Connection connection;
        boolean status = true;
        for (ReceptionistAttendanceMarkTM re : st
        ) {

            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            status = attendanceMarkBO.saveAttendanceMarking(
                    new AttendanceMarkDTO(
                            re.getId(),
                            re.getStID(),
                            re.getActions().isSelected()
                    )
            );
        }
        if (status) {
            return attendanceBO.updateAttendance(
                    new AttendanceDTO(
                            attendanceID,
                            classID,
                            LocalDate.now(),
                            totalSt,
                            false
                    )
            );
        }
        return false;
    }
}
