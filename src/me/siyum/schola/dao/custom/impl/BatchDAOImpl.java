package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.BatchDAO;
import me.siyum.schola.entity.Batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BatchDAOImpl implements BatchDAO {
    @Override
    public boolean save(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(Batch batch) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Batch> search(String s) throws SQLException, ClassNotFoundException {
        ArrayList<Batch> batchList = new ArrayList<>();

        s = "%"+s+"%";
        ResultSet res = CRUDUtil.execute("SELECT * FROM batches");
        while (res.next()){
            batchList.add(
                new Batch(
                        res.getString(1),
                        res.getString(2)
                )
            );
        }
        return batchList;

    }

    @Override
    public ResultSet retrieve(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getID(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
