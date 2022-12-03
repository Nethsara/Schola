package me.siyum.schola.controller.admin;

import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import me.siyum.schola.bo.BOFactory;
import me.siyum.schola.bo.BOTypes;
import me.siyum.schola.bo.custom.FeeBO;
import me.siyum.schola.bo.custom.SalaryBO;
import me.siyum.schola.db.DBConnection;
import me.siyum.schola.dto.FeeDTO;
import me.siyum.schola.dto.SalaryDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminStudentIncomeReportController {
    public LineChart chartExpenses;
    public LineChart chartIncome;

    SalaryBO salaryBO = BOFactory.getInstance().getBO(BOTypes.SALARY);

    public void initialize() {
        setIncome();
        setExpenses();
    }

    private void setExpenses() {
        ArrayList<SalaryDTO> salaries = null;
        try {
            salaries = salaryBO.getSalaries();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        XYChart.Series series = new XYChart.Series();

        for (SalaryDTO s : salaries
        ) {
            series.getData().add(new XYChart.Data(s.getDate().toString(), s.getAmount()));
        }
        chartExpenses.getData().add(series);
    }

    private void setIncome() {
        XYChart.Series series = new XYChart.Series();
        FeeBO feeBO = BOFactory.getInstance().getBO(BOTypes.FEE);
        try {
            ArrayList<FeeDTO> feesAll = feeBO.getFeesAll();


            for (FeeDTO f : feesAll
            ) {
                series.getData().add(new XYChart.Data(f.getDate().toString(), f.getAmount()));

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        chartIncome.getData().add(series);
    }

    public void expenses(ActionEvent actionEvent) {
        try {
            JasperDesign jd = JRXmlLoader.load("F:\\IJSE\\Final Projects\\Schola\\src\\me\\siyum\\schola\\reports\\Expenses.jrxml");
            String sql = "SELECT * FROM salary";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void income(ActionEvent actionEvent) {
        try {
            JasperDesign jd = JRXmlLoader.load("F:\\IJSE\\Final Projects\\Schola\\src\\me\\siyum\\schola\\reports\\Income.jrxml");
            String sql = "SELECT * FROM fee";
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jp, false);
        } catch (JRException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
