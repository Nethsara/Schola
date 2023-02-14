package me.siyum.schola.bo.custom.impl;

import javafx.scene.control.Alert;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.bo.custom.ClassSchedulerBO;
import me.siyum.schola.bo.custom.ClassesBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.dto.ClassesDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class ClassSchedulerBOImpl implements ClassSchedulerBO {

    private final ClassesBO classesBO = (ClassesBO) BOFactory.getInstance().getBO(BOTypes.CLASSES);

    private final AttendanceBO attendanceBO = (AttendanceBO) BOFactory.getInstance().getBO(BOTypes.ATTENDANCE);

    @Override
    public boolean scheduleClass(ClassesDTO classObject, AttendanceDTO attendanceDTO) throws SQLException {
        boolean savedClass = false;
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            savedClass = classesBO.scheduleClass(classObject);
            if (savedClass) {
                boolean savedAttendance = attendanceBO.saveAttendance(attendanceDTO);
                if (savedAttendance) {
                    connection.commit();

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
        return savedClass;

    }
}
