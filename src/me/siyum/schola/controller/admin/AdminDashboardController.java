package me.siyum.schola.controller.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.ClassRoomsBO;
import me.siyum.schola.bo.custom.EmployeeBO;
import me.siyum.schola.bo.custom.StudentBO;
import me.siyum.schola.dto.ClassRoomsDTO;
import me.siyum.schola.dto.EmployeeDTO;
import me.siyum.schola.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDashboardController {
    private final StudentBO studentBO = BOFactory.getInstance().getBO(BOTypes.STUDENT);
    private final EmployeeBO employeeBO = BOFactory.getInstance().getBO(BOTypes.EMPLOYEE);
    private final ClassRoomsBO classRoomsBO = BOFactory.getInstance().getBO(BOTypes.CLASS_ROOMS);

    public Label lblStudentTotal;
    public Label lblTotalEmployee;
    public Label lblTotalCourses;
    public Label lblTotalClassRooms;
    public PieChart pieChart;

    public void initialize() {
        setData();
    }

    private void setData() {
        try {
            setStudentTotal();
            setEmpTotal();
            setCourses();
            setClassRooms();
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
}
