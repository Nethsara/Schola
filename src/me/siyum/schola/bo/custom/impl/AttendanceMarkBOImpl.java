package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.AttendanceMarkBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.AttendanceMarkDAO;
import me.siyum.schola.dto.AttendanceMarkDTO;
import me.siyum.schola.entity.AttendanceMark;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceMarkBOImpl implements AttendanceMarkBO {
    AttendanceMarkDAO attendanceMarkDAO = DAOFactory.getInstance().getDAO(DAOTypes.ATTENDANCE_MARK);

    @Override
    public boolean saveAttendanceMarking(AttendanceMarkDTO attendanceMarkDTO) throws SQLException, ClassNotFoundException {
        return attendanceMarkDAO.save(
                new AttendanceMark(
                        attendanceMarkDTO.getAmID(),
                        attendanceMarkDTO.getStID(),
                        attendanceMarkDTO.isPresent()
                )
        );
    }

    @Override
    public ArrayList<AttendanceMarkDTO> getAttendanceByID(String id) throws SQLException, ClassNotFoundException {
        ArrayList<AttendanceMark> search = attendanceMarkDAO.search(id);
        ArrayList<AttendanceMarkDTO> attendanceMarkDTOS = new ArrayList<>();
        for (AttendanceMark a : search
        ) {
            attendanceMarkDTOS.add(
                    new AttendanceMarkDTO(
                            a.getAmID(),
                            a.getStID(),
                            a.isPresent()
                    )
            );
        }
        return attendanceMarkDTOS;
    }
}
