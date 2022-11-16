package me.siyum.schola.dao.custom;

import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.Employee;
import me.siyum.schola.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee, Integer> {

    ArrayList<Salary> getPayments(int id) throws SQLException, ClassNotFoundException;
    String getPaymentMethod(int id) throws SQLException, ClassNotFoundException;
}
