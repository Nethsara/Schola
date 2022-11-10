package me.siyum.schola.controller;

import javafx.scene.chart.LineChart;

public class AdminDashboardController {
    public LineChart studentGrowthChart;
    public LineChart incomeGrowthChart;

    public void initialize(){
        studentGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        incomeGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

    }
}
