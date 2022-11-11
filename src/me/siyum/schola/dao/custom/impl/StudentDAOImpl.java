package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.StudentDAO;
import me.siyum.schola.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAOImpl implements StudentDAO {
    public boolean save(Student st) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO students VALUES(?,?,?,?,?,?,?,?,?)",
                st.getId(),
                st.getName(),
                st.getEmail(),
                st.getNic(),
                st.getImage(),
                st.getAddress(),
                st.getPhone(),
                st.getParentID(),
                st.getScholaMark());
    }

    @Override
    public int getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM students ORDER BY sID DESC");
        if(res.next()){
            return res.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public ResultSet retrieve(Integer id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM students WHERE sID = ?", id);
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM students");
    }

}
