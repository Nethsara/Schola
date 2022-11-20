package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.TasksDAO;
import me.siyum.schola.entity.Tasks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TasksDAOImpl implements TasksDAO {

    @Override
    public boolean save(Tasks tasks) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO tasks VALUES(?,?,?,?)",
                tasks.getId(), tasks.getTimeStamp(), tasks.getMessage(), tasks.getStatus());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM tasks ORDER BY tskID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Tasks tasks) {
        return false;
    }

    @Override
    public boolean delete(String integer) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM tasks WHERE tskID=?",
                integer);
    }

    public ArrayList<Tasks> search(String s) throws SQLException, ClassNotFoundException {
        ResultSet set = CRUDUtil.execute("SELECT * FROM tasks");
        ArrayList<Tasks> list = new ArrayList<>();
        while (set.next()) {
            list.add(new Tasks(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getBoolean(4))
            );
        }
        return list;
    }

    @Override
    public ResultSet retrieve(String integer) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("SELECT * FROM tasks");
    }

    @Override
    public String getID(String s) {
        return "";
    }

    @Override
    public int getCount() {
        return 0;
    }
}
