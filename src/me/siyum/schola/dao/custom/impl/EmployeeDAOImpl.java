package me.siyum.schola.dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
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
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Employee> search(String s) throws SQLException, ClassNotFoundException {
        return null;
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
        ResultSet resultSet = CRUDUtil.execute("SELECT id FROM login_token WHERE token=?", s);
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    @Override
    public ArrayList<Employee> get(String s) throws SQLException, ClassNotFoundException {
        s = "%" + s + "%";

        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = CRUDUtil.execute("SELECT * FROM employee WHERE role LIKE?", s);
        while (resultSet.next()) {
            employees.add(new Employee(
                    resultSet.getString(1),
                    resultSet.getBlob(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getDouble(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getBoolean(9)
            ));
        }
        return employees;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public ArrayList<Salary> getPayments(ID id) throws SQLException, ClassNotFoundException {
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
    public String getPaymentMethod(ID id) throws SQLException, ClassNotFoundException {
        ResultSet execute = CRUDUtil.execute("SELECT name FROM payment_methods WHERE empID=?", id);
        if (execute.next()) {
            return execute.getString(1);
        }
        return "";
    }


}
