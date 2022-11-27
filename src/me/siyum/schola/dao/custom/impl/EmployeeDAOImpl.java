package me.siyum.schola.dao.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.siyum.schola.dao.CRUDUtil;
import me.siyum.schola.dao.custom.EmployeeDAO;
import me.siyum.schola.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?,?)",
                employee.getId(),
                employee.getImage(),
                employee.getName(),
                employee.getAddress(),
                employee.getEmail(),
                employee.getSalary(),
                employee.getPaymentMethod(),
                employee.getRole(),
                employee.isStatus());
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM employee ORDER BY eID DESC");
        if (res.next()) {
            return res.getString(1);
        }
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
        System.out.println(s);
        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet resultSet = CRUDUtil.execute(s);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
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
    public Employee retrieve(String s) throws SQLException, ClassNotFoundException {
        ResultSet res = CRUDUtil.execute("SELECT * FROM employee WHERE eID=?", s);
        if (res.next()) {
            return new Employee(
                    res.getString(1),
                    res.getBlob(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getDouble(6),
                    res.getString(7),
                    res.getString(8),
                    res.getBoolean(9)
            );
        }
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
    public String getIDByToken(String s, String role) throws SQLException, ClassNotFoundException {
        ResultSet execute = CRUDUtil.execute("SELECT uID FROM login_tokens WHERE token=? && role=?", s, role);
        if (execute.next()) {
            return execute.getString(1);
        }
        return "";
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
