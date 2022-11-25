package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

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
                        st.getImage(),
                        st.getAddress(),
                        st.getPhone(),
                        st.getParentID(),
                        st.getScholaMark(),
                        st.getDob(),
                        st.isStatus(),
                        st.isApproval(),
                        st.getBatch()
                )
        );
    }

    @Override
    public StudentDTO retrieveStudent(String id) throws SQLException, ClassNotFoundException {

        Student t = dao.retrieve(id);

        return new StudentDTO(
                t.getId(),
                t.getName(),
                t.getEmail(),
                t.getAddress(),
                t.getImage(),
                t.getNic(),
                t.getPhone(),
                t.getParentID(),
                t.getScholaMark(),
                t.getDob(),
                t.isStatus(),
                t.isApproval(),
                t.getBatch());

    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public ArrayList<StudentDTO> searchStudents(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = dao.search(s);
        ArrayList<StudentDTO> dtos = new ArrayList<>();
        for (Student t : students) {
            dtos.add(new StudentDTO(
                    t.getId(),
                    t.getName(),
                    t.getEmail(),
                    t.getAddress(),
                    t.getImage(),
                    t.getNic(),
                    t.getPhone(),
                    t.getParentID(),
                    t.getScholaMark(),
                    t.getDob(),
                    t.isStatus(),
                    t.isApproval(),
                    t.getBatch()
            ));
        }
        return dtos;
    }

    @Override
    public ArrayList<StudentDTO> searchStudents(boolean b) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = dao.search(b);
        ArrayList<StudentDTO> dtos = new ArrayList<>();
        for (Student t : students) {
            dtos.add(new StudentDTO(
                    t.getId(),
                    t.getName(),
                    t.getEmail(),
                    t.getAddress(),
                    t.getImage(),
                    t.getNic(),
                    t.getPhone(),
                    t.getParentID(),
                    t.getScholaMark(),
                    t.getDob(),
                    t.isStatus(),
                    t.isApproval(),
                    t.getBatch()
            ));
        }
        return dtos;
    }

    @Override
    public boolean updateStudent(StudentDTO st) throws SQLException, ClassNotFoundException {
        return dao.update(
                new Student(
                        st.getId(),
                        st.getName(),
                        st.getEmail(),
                        st.getNic(),
                        st.getImage(),
                        st.getAddress(),
                        st.getPhone(),
                        st.getParentID(),
                        st.getScholaMark(),
                        st.getDob(),
                        st.isStatus(),
                        st.isApproval(),
                        st.getBatch()
                )
        );
    }

    @Override
    public String getStudentByToken(String token) throws SQLException, ClassNotFoundException {
        return dao.getIDByToken(token, "student");
    }
}
