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

    @Override
    public ArrayList<EmployeeDTO> filterEmployees(String filter) throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> returnList = new ArrayList<>();
        if (filter.equalsIgnoreCase("id asc")) {
            ArrayList<Employee> filteredEmp = dao.search("SELECT * FROM employee ORDER BY eID ASC");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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

            return returnList;
        } else if (filter.equalsIgnoreCase("id desc")) {
            ArrayList<Employee> filteredEmp = dao.search("SELECT * FROM employee ORDER BY eID DESC");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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

            return returnList;
        } else if (filter.equalsIgnoreCase("name asc")) {
            ArrayList<Employee> filteredEmp = dao.search("SELECT * FROM employee ORDER BY name ASC");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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

            return returnList;
        } else if (filter.equalsIgnoreCase("name desc")) {
            ArrayList<Employee> filteredEmp = dao.search("SELECT * FROM employee ORDER BY name DESC");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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

            return returnList;
        } else if (filter.equalsIgnoreCase("receptionist")) {
            ArrayList<Employee> filteredEmp = dao.get("receptionist");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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
            return returnList;

        } else if (filter.equalsIgnoreCase("lecturers")) {
            ArrayList<Employee> filteredEmp = dao.get("lecturer");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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
            return returnList;

        } else if (filter.equalsIgnoreCase("minor")) {
            ArrayList<Employee> filteredEmp = dao.get("minor");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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
            return returnList;

        } else if (filter.equalsIgnoreCase("secretary")) {
            ArrayList<Employee> filteredEmp = dao.get("secretary");
            for (Employee e : filteredEmp
            ) {
                returnList.add(new EmployeeDTO(
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
            return returnList;

        }
        return null;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return dao.update(new Employee(
                employeeDTO.getId(),
                employeeDTO.getImage(),
                employeeDTO.getName(),
                employeeDTO.getAddress(),
                employeeDTO.getEmail(),
                employeeDTO.getSalary(),
                employeeDTO.getPaymentMethod(),
                employeeDTO.getRole(),
                employeeDTO.isStatus()
        ));
    }


}
