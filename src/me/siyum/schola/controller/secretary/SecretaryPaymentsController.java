package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.SalaryBO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.view.secretary.tm.SecretarySalaryTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SecretaryPaymentsController {
    private final SalaryBO bo = BOFactory.getInstance().getBO(BOTypes.SALARY);
    private final EmployeeBO empBo = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public JFXTextField txtEmpName;
    public JFXTextField txtPayable;
    public JFXTextField txtAmount;
    public TableColumn colID;
    public TableColumn colTo;
    public TableColumn colAmount;
    public TableColumn colDate;
    public JFXComboBox<String> cmbEmployeeID;
    public TableView<SecretarySalaryTM> tblPayment;

    private EmployeeDTO employeeDTO;

    public void initialize() {

        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTo.setCellValueFactory(new PropertyValueFactory<>("to"));

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
        System.out.println("Setting table");
        ArrayList<SalaryDTO> salaries = bo.getSalaries();
        ObservableList<SecretarySalaryTM> list = FXCollections.observableArrayList();
        for (SalaryDTO s : salaries
        ) {
            System.out.println(s.getId());
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

    public void setEmployeeData() throws SQLException, ClassNotFoundException {
        employeeDTO = empBo.getEmployeeByID(cmbEmployeeID.getValue());
        txtEmpName.setText(employeeDTO.getName());
        txtPayable.setText(String.valueOf(employeeDTO.getSalary()));
    }

    private String generateID() throws SQLException, ClassNotFoundException {
        String lastID = empBo.getLastID();

        if (lastID.equalsIgnoreCase("")) {
            return "SEMS-" + 1;
        } else {
            String[] array = lastID.split("-");
            int tempNumber = Integer.parseInt(array[1]);
            int finalizeOrderId = tempNumber + 1;
            return "SEMS-" + finalizeOrderId;
        }
    }

    public void payEmp(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (Double.parseDouble(txtAmount.getText()) > employeeDTO.getSalary()) {
            new Alert(Alert.AlertType.WARNING, "Amount is greater than payable amount, Please check values").show();
        } else {
            boolean b = bo.saveSalary(new SalaryDTO(
                    generateID(),
                    cmbEmployeeID.getValue(),
                    LocalDate.now(),
                    Double.parseDouble(txtAmount.getText())
            ));

            if (b) {
                new Alert(Alert.AlertType.INFORMATION, "Success").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed").show();
            }
            setTable();
        }
    }
}
