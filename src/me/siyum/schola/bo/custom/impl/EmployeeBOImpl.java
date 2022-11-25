package me.siyum.schola.bo.custom.impl;

import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.dao.DAOFactory;
import me.siyum.schola.dao.DAOTypes;
import me.siyum.schola.dao.custom.impl.EmployeeDAOImpl;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAOImpl dao = DAOFactory.getInstance().getDAO(DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDTO> getEmployeeByType(String type) throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employee = dao.get(type);
        ArrayList<EmployeeDTO> emp = new ArrayList<>();
        for (Employee e : employee
        ) {
            emp.add(new EmployeeDTO(
                    e.getId(),
                    e.getImage(),
                    e.getName(),
                    e.getAddress(),
                    e.getEmail(),
                    e.getSalary(),
                    e.getPaymentMethod(),
                    e.getRole(),
                    e.isStatus()
            ));
        }

        return emp;
    }

    @Override
    public EmployeeDTO getEmployeeByID(String id) throws SQLException, ClassNotFoundException {
        Employee retrieve = dao.retrieve(id);
        return new EmployeeDTO(
                retrieve.getId(),
                retrieve.getImage(),
                retrieve.getName(),
                retrieve.getAddress(),
                retrieve.getEmail(),
                retrieve.getSalary(),
                retrieve.getPaymentMethod(),
                retrieve.getRole(),
                retrieve.isStatus()
        );
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return dao.save(new Employee(
                employeeDTO.getId(),
                employeeDTO.getImage(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getEmail(),
                employeeDTO.getSalary(),
                employeeDTO.getPaymentMethod(),
                employeeDTO.getRole(),
                true
        ));
    }

    @Override
    public String getLastID() throws SQLException, ClassNotFoundException {
        return dao.getLastID();
    }

    @Override
    public String getIDByToken(String token, String role) throws SQLException, ClassNotFoundException {
        return dao.getIDByToken(token, role);
    }


}
