package me.siyum.schola.controller.secretary;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SecretaryPaymentsController {
    public JFXComboBox cmbEmployeeID;
    public JFXTextField txtEmpName;
    public JFXTextField txtPayable;
    public JFXTextField txtAmount;
    public TableView tblPayment;
    public TableColumn colID;
    public TableColumn colTo;
    public TableColumn colAmount;
    public TableColumn colDate;

    public void setEmployeeData(ActionEvent actionEvent) {
    }

    public void payEmp(ActionEvent actionEvent) {
    }
}
