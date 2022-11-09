package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentBOImpl implements StudentBO {

    private StudentDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO st) throws SQLException, ClassNotFoundException {
        return dao.save(
                new Student(
                        st.getId(),
                        st.getName(),
                        st.getEmail(),
                        st.getNic(),
                        st.getAddress(),
                        st.getImage(),
                        st.getPhone(),
                        st.getParentID(),
                        st.getScholaMark()
                )
        );
    }

    @Override
    public ResultSet retrieveStudent(int id) {
        return null;
    }

    @Override
    public int getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }
}
