package me.siyum.schola.controller.secretary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.view.secretary.tm.SecretaryDashboardStTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class SecretaryDashboardController {
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    public Label lblTotalStudents;
    public Label lblPendingStudents;
    public Label lblPaymentReceived;
    public Label lblPaymentSent;
    public PieChart pieChart;
    public TableColumn<SecretaryDashboardStTM, String> colID;
    public TableColumn<SecretaryDashboardStTM, String> colName;
    public TableColumn<SecretaryDashboardStTM, String> colEmail;
    public TableColumn<SecretaryDashboardStTM, String> colSchola;
    public TableColumn<SecretaryDashboardStTM, String> colAction;
    public TableColumn<SecretaryDashboardStTM, String> colPID;
    public TableColumn<SecretaryDashboardStTM, String> colPName;
    public TableColumn<SecretaryDashboardStTM, String> colPEmail;
    public TableColumn<SecretaryDashboardStTM, String> colPActions;
    public TableView<SecretaryDashboardStTM> tblSt;

    public void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSchola.setCellValueFactory(new PropertyValueFactory<>("scholaMark"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
        setData();
    }

    private void setData() {
        try {
            setPieChart();
            setLabels();
            setTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setTables() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");
        ObservableList<SecretaryDashboardStTM> st = FXCollections.observableArrayList();
        for (StudentDTO s : studentDTOS
        ) {
            Button btn = new Button("View");
            st.add(
                    new SecretaryDashboardStTM(
                            s.getId(),
                            s.getName(),
                            s.getEmail(),
                            s.getScholaMark(),
                            btn
                    )
            );
        }
        tblSt.setItems(st);

    }

    private void setLabels() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");
        lblTotalStudents.setText(String.valueOf(studentDTOS.size()));
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

    class ScholaComparator implements Comparator<StudentDTO> {

        // override the compare() method
        public int compare(StudentDTO s1, StudentDTO s2) {
            if (s1.getScholaMark() == s2.getScholaMark())
                return 0;
            else if (s1.getScholaMark() > s2.getScholaMark())
                return 1;
            else
                return -1;
        }
    }
}
