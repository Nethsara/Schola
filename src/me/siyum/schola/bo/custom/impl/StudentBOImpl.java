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

    private final StudentDAO dao = DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

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

    @Override
    public ArrayList<StudentDTO> filterStudents(String s, String exm) throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> students = new ArrayList<>();

        if (s.equalsIgnoreCase("weak")) {
            ArrayList<Student> filteredStudents = dao.filterByMarks("mark DESC", exm);
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("top")) {
            System.out.println("Order by top");
            ArrayList<Student> filteredStudents = dao.filterByMarks("mark ASC", exm);
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("id asc")) {
            System.out.println("id asc");
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY stID ASC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("id desc")) {
            System.out.println("id desc");
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY stID DESC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("name asc")) {
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY name ASC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("name desc")) {
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY name DESC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("schola asc")) {
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY scholaMarks ASC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        } else if (s.equalsIgnoreCase("schola desc")) {
            ArrayList<Student> filteredStudents = dao.filter("SELECT * FROM students ORDER BY scholaMarks DESC");
            for (Student t : filteredStudents) {
                students.add(
                        new StudentDTO(t.getId(), t.getName(), t.getEmail(), t.getAddress(), t.getImage(), t.getNic(),
                                t.getPhone(), t.getParentID(), t.getScholaMark(), t.getDob(), t.isStatus(), t.isApproval(),
                                t.getBatch()
                        ));

            }
            return students;
        }
        return null;
    }
}
