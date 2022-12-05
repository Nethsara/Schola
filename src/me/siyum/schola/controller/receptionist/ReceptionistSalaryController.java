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
import me.siyum.schola.bo.custom.SalaryBO;
import me.siyum.schola.dto.SalaryDTO;
import me.siyum.schola.util.Env;
import me.siyum.schola.view.receptionist.tm.ReceptionistSalaryTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReceptionistSalaryController {
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final SalaryBO salaryBO = BOFactory.getInstance().getBO(BOTypes.SALARY);
    public TableColumn colID;
    public TableColumn colDate;
    public TableColumn colMethod;
    public TableColumn colAmount;
    public AnchorPane paneRecSalaryHistory;
    public TableView<ReceptionistSalaryTM> tblRecSalary;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        setData();
    }

    public void setData() {
        try {
            ObservableList<ReceptionistSalaryTM> tmList = FXCollections.observableArrayList();

            String lectureID = employeeBO.getIDByToken(Env.token, "receptionist");
            ArrayList<SalaryDTO> salaryDTOS = salaryBO.getSalaries(lectureID);

            for (SalaryDTO s : salaryDTOS) {
                tmList.add(
                        new ReceptionistSalaryTM(
                                s.getId(),
                                s.getEmpID(),
                                s.getDate(),
                                s.getAmount()
                        )
                );
            }
            tblRecSalary.setItems(tmList);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
