package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean save(Student st) throws SQLException, ClassNotFoundException {
        String nic = st.getNic();
        char[] charArray = nic.toCharArray();
        String gender = "";
        int dayOfYear = charArray[5] + charArray[6] + charArray[7];

        if (dayOfYear > 500) {
            gender = "female";
        } else {
            gender = "male";
        }
        return CRUDUtil.execute("INSERT INTO students VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
                st.getBatch(),
                gender,
                LocalDate.now()
        );
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM students ORDER BY stID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Student s) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("UPDATE students SET name=?,email=?,nic=?,image=?," +
                        "address=?,phone=?,pID=?,scholaMarks=?,dob=?,status=?,approval=?, batch=? WHERE stid=?",
                s.getName(), s.getEmail(), s.getNic(), s.getImage(), s.getAddress(), s.getPhone(), s.getParentID(), s.getScholaMark(), s.getDob(), s.isStatus(), s.isApproval(), s.getBatch(), s.getId());
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ArrayList<Student> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();

        s = "%" + s + "%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM students WHERE stID LIKE ? || name LIKE ? || email LIKE ?",
                s, s, s);
        while (res.next()) {
            students.add(
                    new Student(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getBlob(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            res.getInt(9),
                            res.getDate(10).toLocalDate(),
                            res.getBoolean(11),
                            res.getBoolean(12),
                            res.getString(13),
                            res.getString(14),
                            res.getDate(15).toLocalDate()
                    )
            );
        }
        return students;
    }

    @Override
    public Student retrieve(String id) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM students WHERE stID = ?", id);
        if (res.next()) {
            return new Student(
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getBlob(5),
                    res.getString(6),
                    res.getString(7),
                    res.getString(8),
                    res.getInt(9),
                    res.getDate(10).toLocalDate(),
                    res.getBoolean(11),
                    res.getBoolean(12),
                    res.getString(13),
                    res.getString(14),
                    res.getDate(15).toLocalDate()
            );
        }
        return null;
    }


    @Override
    public String getID(String s) {
        return "";
    }

    @Override
    public String getIDByToken(String s, String role) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT uID FROM login_tokens WHERE token= ? ||  role=?",
                s, role);
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }


    @Override
    public ArrayList<Student> search(boolean b) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();

        ResultSet res = CRUDUtil.execute("SELECT * FROM students WHERE approval=? && status=?", b, !b);

        while (res.next()) {
            students.add(
                    new Student(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getBlob(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            res.getInt(9),
                            res.getDate(10).toLocalDate(),
                            res.getBoolean(11),
                            res.getBoolean(12),
                            res.getString(13),
                            res.getString(14),
                            res.getDate(15).toLocalDate()
                    )
            );
        }
        return students;
    }

    @Override
    public ArrayList<Student> filter(String filter) throws SQLException, ClassNotFoundException {
        ArrayList<Student> students = new ArrayList<>();

        ResultSet res = CRUDUtil.execute(filter);
        while (res.next()) {
            System.out.println("id " + res.getString(1) + " name " + res.getString(2));
            students.add(
                    new Student(
                            res.getString(1),
                            res.getString(2),
                            res.getString(3),
                            res.getString(4),
                            res.getBlob(5),
                            res.getString(6),
                            res.getString(7),
                            res.getString(8),
                            res.getInt(9),
                            res.getDate(10).toLocalDate(),
                            res.getBoolean(11),
                            res.getBoolean(12),
                            res.getString(13),
                            res.getString(14),
                            res.getDate(15).toLocalDate()
                    )
            );
        }
        return students;
    }

    @Override
    public ArrayList<Student> filterByMarks(String filter, String exm) throws SQLException, ClassNotFoundException {
        ArrayList<Student> sts = new ArrayList<>();
        ResultSet res = CRUDUtil.execute("SELECT stID FROM student_exam_marks WHERE examID=? ORDER BY ?", exm, filter);
        while (res.next()) {
            sts.add(
                    retrieve(res.getString(1))
            );
        }
        return sts;
    }
}
