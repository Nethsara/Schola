package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    ArrayList<SalaryDTO> getSalaries(int id) throws SQLException, ClassNotFoundException;
    int getEmployeeID(String token) throws SQLException, ClassNotFoundException;
    String getPaymentMethod(int id) throws SQLException, ClassNotFoundException;
}
