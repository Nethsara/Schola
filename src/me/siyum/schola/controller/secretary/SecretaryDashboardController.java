package me.siyum.schola.controller.secretary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SecretaryDashboardController {
    public Label lblTotalStudents;
    public Label lblPendingStudents;
    public Label lblPaymentReceived;
    public Label lblPaymentSent;
    public PieChart pieChart;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colSchola;
    public TableColumn colAction;
    public TableColumn colPID;
    public TableColumn colPName;
    public TableColumn colPEmail;
    public TableColumn colPActions;

    private StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            setPieChart();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setPieChart() throws SQLException, ClassNotFoundException {
        int male = 0;
        int female = 0;
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");
        for (StudentDTO s : studentDTOS
        ) {
            if (s.getGender().equalsIgnoreCase("male")) {
                male++;
            } else {
                female++;
            }
        }
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        pieChartData.add(new PieChart.Data("Male", male));
        pieChartData.add(new PieChart.Data("Female", female));

        pieChart.setData(pieChartData);
    }
}
