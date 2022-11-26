package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.SalaryDAO;
import me.siyum.schola.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO salary VALUES(?,?,?,?)",
                salary.getId(), salary.getEmpID(), salary.getDate(), salary.getAmount());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM salary ORDER BY sID DESC");
        if (res.next()) {
            return res.getString(1);
        }
        return "";
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Salary> search(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM salary WHERE empID LIKE ?", s);
        ArrayList<Salary> payments = new ArrayList<>();
        while (res.next()) {
            payments.add(
                    new Salary(
                            res.getString(1),
                            res.getString(2),
                            res.getDate(3).toLocalDate(),
                            res.getDouble(4)
                    )
            );
        }
        return payments;
    }

    @Override
    public Salary retrieve(String s) throws SQLException, ClassNotFoundException {
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

    @Override
    public String getIDByToken(String s, String role) {
        return null;
    }
}
