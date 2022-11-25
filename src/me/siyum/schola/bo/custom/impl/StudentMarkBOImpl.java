package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.StudentMarkDAO;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.entity.StudentMarks;

import java.sql.SQLException;

public class StudentMarkBOImpl implements StudentMarkBO {
    StudentMarkDAO studentMarkDAO = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT_MARK);

    @Override
    public double getMarkByID(String stID, String exmID) throws SQLException, ClassNotFoundException {
        return studentMarkDAO.getExmMarkByIDs(stID, exmID);
    }

    @Override
    public boolean saveExmStMarks(StudentMarkDTO stMarkDTO) throws SQLException, ClassNotFoundException {
        return studentMarkDAO.save(new StudentMarks(
                stMarkDTO.getId(),
                stMarkDTO.getExmID(),
                stMarkDTO.getsID(),
                stMarkDTO.getMark()
        ));
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return studentMarkDAO.getLastID();
    }


}
