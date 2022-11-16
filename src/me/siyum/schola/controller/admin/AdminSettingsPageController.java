package me.siyum.schola.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;

public class AdminSettingsPageController {
    public static class AdminDashboardController {
        public LineChart studentGrowthChart;
        public LineChart incomeGrowthChart;


        public void initialize(){
            studentGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
            incomeGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

        }


        public void newStudent(ActionEvent actionEvent) {
        }
    }
}
