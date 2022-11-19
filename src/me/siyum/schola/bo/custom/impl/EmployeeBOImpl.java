package me.siyum.schola.bo.custom.impl;

import com.sun.xml.internal.bind.v2.model.core.ID;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.impl.EmployeeDAOImpl;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<SalaryDTO> getSalaries(ID id) throws SQLException, ClassNotFoundException {
        ArrayList<Salary> payments = dao.getPayments(id);
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
    public String getEmployeeID(String token) throws SQLException, ClassNotFoundException {
        return dao.getID(token);
    }

    @Override
    public String getPaymentMethod(ID id) throws SQLException, ClassNotFoundException {
        return dao.getPaymentMethod(id);
    }
}
