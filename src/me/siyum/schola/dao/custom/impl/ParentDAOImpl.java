package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.ParentDAO;
import me.siyum.schola.entity.Parent;
import me.siyum.schola.entity.Tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParentDAOImpl implements ParentDAO {
    @Override
    public boolean save(Parent parent) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO parents VALUES(?,?,?,?,?,?)",
                parent.getId(),
                parent.getName(),
                parent.getEmail(),
                parent.getNic(),
                parent.getAddress(),
                parent.getPhone()
        );
    }

    @Override
    public int getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM parents ORDER BY pID DESC");
        if(res.next()){
            return res.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean update(Parent parent) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public ArrayList<Parent> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve(Integer id) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM parents WHERE pID = ?", id);
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM parents");
    }
}
