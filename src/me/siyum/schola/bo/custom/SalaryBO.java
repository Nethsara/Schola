package me.siyum.schola.bo.custom;

import me.siyum.schola.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO {
    ArrayList<SalaryDTO> getSalaries() throws SQLException, ClassNotFoundException;

    boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException;
}
