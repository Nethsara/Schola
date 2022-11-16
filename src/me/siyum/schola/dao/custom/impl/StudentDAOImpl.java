package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    public boolean save(Student st) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO students VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
        );
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM students ORDER BY stID DESC");
        if(res.next()){
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public ArrayList<Student> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ResultSet retrieve(Integer id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM students WHERE stID = ?", id);
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM students");
    }

    @Override
    public int getID(String s) {
        return 0;
    }


}
