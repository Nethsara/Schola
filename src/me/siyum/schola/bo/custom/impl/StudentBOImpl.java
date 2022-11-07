package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.entity.Student;

public class StudentBOImpl implements StudentBO {

    private StudentDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public void save(StudentDTO student) {
        dao.save(new Student());
    }
}
