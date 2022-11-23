package me.siyum.schola.bo.custom;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.siyum.schola.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO {
    String getEmployeeID(String token) throws SQLException, ClassNotFoundException;

    String getPaymentMethod(ID id) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> getEmployeeByType(String type) throws SQLException, ClassNotFoundException;

    EmployeeDTO getEmployeeByID(String id);
}
