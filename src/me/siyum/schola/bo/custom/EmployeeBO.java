package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {

    ArrayList<EmployeeDTO> getEmployeeByType(String type) throws SQLException, ClassNotFoundException;

    EmployeeDTO getEmployeeByID(String id) throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    String getLastID() throws SQLException, ClassNotFoundException;

    String getIDByToken(String token, String role) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDTO> filterEmployees(String filter) throws SQLException, ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
}
