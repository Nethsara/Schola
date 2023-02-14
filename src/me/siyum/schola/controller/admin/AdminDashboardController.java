package me.siyum.schola.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ClassRoomsBO;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.LecturerScholaBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.ClassRoomsDTO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.dto.StudentDTO;
import me.siyum.schola.util.Navigation;
import me.siyum.schola.util.Routes;
import me.siyum.schola.view.admin.tm.AdminDashboardEmpTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class AdminDashboardController {
    private final StudentBO studentBO = (StudentBO) BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final EmployeeBO employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final ClassRoomsBO classRoomsBO = (ClassRoomsBO) BOFactory.getInstance().getBO(BOTypes.CLASS_ROOMS);
    public Label lblStudentTotal;
    public Label lblTotalEmployee;
    public Label lblTotalCourses;
    public Label lblTotalClassRooms;
    public PieChart pieChart;
    public LineChart paymentsGrowth;
    public TableColumn<AdminDashboardEmpTM, String> colID;
    public TableColumn<AdminDashboardEmpTM, String> colName;
    public TableColumn<AdminDashboardEmpTM, Integer> colScholaMark;
    public TableView<AdminDashboardEmpTM> tblLecturers;
    public AnchorPane mainPane;
    LecturerScholaBO lecturerScholaBO = (LecturerScholaBO) BOFactory.getInstance().getBO(BOTypes.LECTURER_SCHOLA);
    ObservableList<AdminDashboardEmpTM> obList = FXCollections.observableArrayList();

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            colID.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colScholaMark.setCellValueFactory(new PropertyValueFactory<>("scholaMark"));

            setStudentTotal();
            setEmpTotal();
            setCourses();
            setClassRooms();
            setPieChart();
            setLineCharts();
            setEmployeeList();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setEmployeeList() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeByType = employeeBO.getEmployeeByType("");
        for (EmployeeDTO e : employeeByType
        ) {
            if (employeeBO.getEmployeeByID(e.getId()).getRole().equalsIgnoreCase("lecturer")) {
                Circle c = new Circle();

                int lecturer = employeeBO.getEmployeeByID(e.getId()).getRole().equalsIgnoreCase("lecturer") ?
                        lecturerScholaBO.getScholaByID(e.getId()).getMark() : 0;
                obList.add(
                        new AdminDashboardEmpTM(
                                e.getId(),
                                c,
                                e.getName(),
                                lecturer
                        )
                );
            }
        }
        obList.sort(Comparator.comparing(AdminDashboardEmpTM::getScholaMark).reversed());

        tblLecturers.setItems(obList);
    }

    private void setLineCharts() {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("June", 23));
        series.getData().add(new XYChart.Data("July", 65));
        series.getData().add(new XYChart.Data("August", 68));
        series.getData().add(new XYChart.Data("September", 32));
        series.getData().add(new XYChart.Data("October", 56));
        series.getData().add(new XYChart.Data("November", 76));
        series.getData().add(new XYChart.Data("December", 5));
        paymentsGrowth.getData().add(series);
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

    private void setClassRooms() throws SQLException, ClassNotFoundException {
        ArrayList<ClassRoomsDTO> allClassRooms = classRoomsBO.getAllClassRooms("");
        lblTotalClassRooms.setText(String.valueOf(allClassRooms.size()));
    }

    private void setCourses() {
    }

    private void setEmpTotal() throws SQLException, ClassNotFoundException {
        ArrayList<EmployeeDTO> employeeByType = employeeBO.getEmployeeByType("");
        lblTotalEmployee.setText(String.valueOf(employeeByType.size()));
    }

    private void setStudentTotal() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> studentDTOS = studentBO.searchStudents("");
        lblStudentTotal.setText(String.valueOf(studentDTOS.size()));
    }

    public void lecturerPage(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.ADMIN_EMPLOYEE, mainPane);
    }
}
