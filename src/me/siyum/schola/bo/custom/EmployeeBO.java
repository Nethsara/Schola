package me.siyum.schola.bo.custom;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.siyum.schola.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    ArrayList<SalaryDTO> getSalaries(ID id) throws SQLException, ClassNotFoundException;
    String getEmployeeID(String token) throws SQLException, ClassNotFoundException;
    String getPaymentMethod(ID id) throws SQLException, ClassNotFoundException;
}
