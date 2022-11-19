package me.siyum.schola.dao.custom;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.siyum.schola.dao.CrudDAO;
import me.siyum.schola.entity.Employee;
import me.siyum.schola.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO extends CrudDAO<Employee, String> {

    ArrayList<Salary> getPayments(ID id) throws SQLException, ClassNotFoundException;
    String getPaymentMethod(ID id) throws SQLException, ClassNotFoundException;
    String getID(String s) throws SQLException, ClassNotFoundException;
}
