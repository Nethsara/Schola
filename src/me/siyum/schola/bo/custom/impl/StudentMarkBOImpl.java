package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.StudentMarkBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.StudentMarkDAO;
import me.siyum.schola.dto.StudentMarkDTO;
import me.siyum.schola.entity.StudentMarks;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentMarkBOImpl implements StudentMarkBO {
    StudentMarkDAO studentMarkDAO = (StudentMarkDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT_MARK);

    @Override
    public double getMarkByID(String stID, String exmID) throws SQLException, ClassNotFoundException {
        return studentMarkDAO.getExmMarkByIDs(stID, exmID);
    }

    @Override
    public ArrayList<StudentMarkDTO> getMarksByID(String stID) throws SQLException, ClassNotFoundException {
        ArrayList<StudentMarks> examMarksByID = studentMarkDAO.getExamMarksByID(stID);
        ArrayList<StudentMarkDTO> arrayList = new ArrayList<>();
        for (StudentMarks m : examMarksByID
        ) {
            arrayList.add(
                    new StudentMarkDTO(
                            m.getId(),
                            m.getExmID(),
                            m.getsID(),
                            m.getMark()
                    )
            );
        }
        return arrayList;
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
