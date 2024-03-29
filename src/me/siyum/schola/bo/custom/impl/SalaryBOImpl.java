package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.SalaryBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.SalaryDAO;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO dao = (SalaryDAO) DAOFactory.getInstance().getDAO(DAOTypes.SALARY);

    @Override
    public ArrayList<SalaryDTO> getSalaries() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> payments = dao.search("");
        ArrayList<SalaryDTO> obList = new ArrayList<>();
        for (Salary s : payments) {
            obList.add(new SalaryDTO(
                    s.getId(),
                    s.getEmpID(),
                    s.getDate(),
                    s.getAmount()
            ));
        }
        return obList;
    }

    @Override
    public ArrayList<SalaryDTO> getSalaries(String id) throws SQLException, ClassNotFoundException {
        ArrayList<Salary> payments = dao.search("%" + id + "%");
        ArrayList<SalaryDTO> obList = new ArrayList<>();
        for (Salary s : payments) {
            obList.add(new SalaryDTO(
                    s.getId(),
                    s.getEmpID(),
                    s.getDate(),
                    s.getAmount()
            ));
        }
        return obList;
    }

    @Override
    public boolean saveSalary(SalaryDTO salary) throws SQLException, ClassNotFoundException {
        return dao.save(new Salary(
                salary.getId(),
                salary.getEmpID(),
                salary.getDate(),
                salary.getAmount()
        ));
    }
}
