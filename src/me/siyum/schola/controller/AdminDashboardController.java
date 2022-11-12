package me.siyum.schola.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

public class AdminDashboardController {
    public LineChart studentGrowthChart;
    public LineChart incomeGrowthChart;


    public void initialize(){
        studentGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        incomeGrowthChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

    }


}
