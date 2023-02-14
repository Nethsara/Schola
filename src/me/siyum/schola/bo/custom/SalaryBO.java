package me.siyum.schola.bo.custom;

import me.siyum.schola.bo.SuperBO;
import me.siyum.schola.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDTO> getSalaries() throws SQLException, ClassNotFoundException;

    ArrayList<SalaryDTO> getSalaries(String id) throws SQLException, ClassNotFoundException;

    boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;
}
