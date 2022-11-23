package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.SalaryBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.view.secretary.tm.SecretarySalaryTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class SecretaryPaymentsController {
    private final SalaryBO bo = BOFactory.getInstance().getBO(BOTypes.SALARY);
    public JFXTextField txtEmpName;
    public JFXTextField txtPayable;
    public JFXTextField txtAmount;
    private final EmployeeBO empBo = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public TableColumn colID;
    public TableColumn colTo;
    public TableColumn colAmount;
    public TableColumn colDate;
    public JFXComboBox<String> cmbEmployeeID;
    public TableView<SecretarySalaryTM> tblPayment;

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            setTable();
            setEmpCmb();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setEmpCmb() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employee = empBo.getEmployeeByType("");
        ObservableList<String> empIDs = FXCollections.observableArrayList();
        for (EmployeeDTO e : employee
        ) {
            empIDs.add(
                    e.getId()
            );
        }
        cmbEmployeeID.setItems(empIDs);
    }

    private void setTable() throws SQLException, ClassNotFoundException {
        ArrayList<SalaryDTO> salaries = bo.getSalaries();
        ObservableList<SecretarySalaryTM> list = FXCollections.observableArrayList();
        for (SalaryDTO s : salaries
        ) {
            list.add(
                    new SecretarySalaryTM(
                            s.getId(),
                            s.getEmpID(),
                            s.getAmount(),
                            s.getDate()
                    )
            );
        }
        tblPayment.setItems(list);
    }

    public void setEmployeeData(ActionEvent actionEvent) {
        EmployeeDTO employee = empBo.getEmployeeByID(cmbEmployeeID.getValue().toString());
        txtEmpName.setText(employee.getName());
        txtPayable.setText(String.valueOf(employee.getSalary()));
    }

    public void payEmp(ActionEvent actionEvent) {
    }
}
