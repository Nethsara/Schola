package me.siyum.schola.dao.custom.impl;

import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.EmployeeDAO;
import me.siyum.schola.entity.Employee;
import me.siyum.schola.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public boolean update(Employee employee) {
        return false;
    }

    @Override
    public boolean delete(String integer) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Employee> search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }


    @Override
    public ResultSet retrieve(Integer integer) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ResultSet retrieve() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getID(String s) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = CRUDUtil.execute("SELECT id FROM login_token WHERE token=?", s);
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    @Override
    public ArrayList<Salary> getPayments(int id) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM payments WHERE empID=?", id);
        ArrayList<Salary> payments = new ArrayList<>();
        while (res.next()) {
            payments.add(
                    new Salary(
                            res.getInt(1),
                            res.getInt(2),
                            res.getDate(3).toLocalDate(),
                            res.getDouble(4)
                    )
            );
        }
        return payments;
    }

    @Override
    public String getPaymentMethod(int id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CRUDUtil.execute("SELECT name FROM payment_methods WHERE empID=?", id);
        if (execute.next()) {
            return execute.getString(1);
        }
        return "";
    }
}
