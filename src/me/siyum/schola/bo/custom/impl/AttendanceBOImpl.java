package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.AttendanceBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.AttendanceDAO;
import me.siyum.schola.dto.AttendanceDTO;
import me.siyum.schola.entity.Attendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO = DAOFactory.getInstance().getDAO(DAOTypes.ATTENDANCE);

    @Override
    public boolean saveAttendance(AttendanceDTO attendanceDTO) throws SQLException, ClassNotFoundException {
        return attendanceDAO.save(
                new Attendance(
                        attendanceDTO.getId(),
                        attendanceDTO.getClassID(),
                        attendanceDTO.getDate(),
                        attendanceDTO.getTotalSt(),
                        attendanceDTO.isStatus()
                ));
    }

    @Override
    public ArrayList<AttendanceDTO> getAllAttendance() throws SQLException, ClassNotFoundException {
        ArrayList<Attendance> search = attendanceDAO.search("");
        ArrayList<AttendanceDTO> returnList = new ArrayList<>();
        for (Attendance a : search
        ) {
            returnList.add(new AttendanceDTO(
                    a.getId(),
                    a.getClassID(),
                    a.getDate(),
                    a.getTotalSt(),
                    a.isStatus()
            ));
        }
        return returnList;
    }

    @Override
    public AttendanceDTO getAttendanceByID(String id) throws SQLException, ClassNotFoundException {
        Attendance retrieve = attendanceDAO.retrieve(id);
        return new AttendanceDTO(
                retrieve.getId(),
                retrieve.getClassID(),
                retrieve.getDate(),
                retrieve.getTotalSt(),
                retrieve.isStatus()
        );
    }
}
