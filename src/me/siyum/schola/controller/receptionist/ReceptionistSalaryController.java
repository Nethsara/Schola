package me.siyum.schola.controller.receptionist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.controller.logincontrol.LoginController;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.view.receptionist.tm.SalaryTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistSalaryController {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    public TableColumn colID;
    public TableColumn colDate;
    public TableColumn colMethod;
    public TableColumn colAmount;
    public AnchorPane paneRecSalaryHistory;
    public TableView<SalaryTM> tblRecSalary;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        //setData();
    }

//    public void setData() {
//        //int userID = LoginController.getUser().getId();
//        ObservableList<SalaryTM> tmList = FXCollections.observableArrayList();
//        try {
//            ArrayList<SalaryDTO> salaryDTOS = employeeBO.getSalaries(userID);
//
//            for (SalaryDTO s : salaryDTOS) {
//                tmList.add(
//                        new SalaryTM(
//                                s.getId(),
//                                s.getEmpID(),
//                                s.getDate(),
//                                s.getAmount(),
//                                employeeBO.getPaymentMethod(s.getId())
//                        )
//                );
//            }
//            tblRecSalary.setItems(tmList);
//
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
