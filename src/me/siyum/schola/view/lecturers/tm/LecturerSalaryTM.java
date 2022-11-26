package me.siyum.schola.view.lecturers.tm;

import java.time.LocalDate;

public class LecturerSalaryTM {
    private String id;
    private String empID;
    private LocalDate date;
    private double amount;

    public LecturerSalaryTM(String id, String empID, LocalDate date, double amount) {
        this.id = id;
        this.empID = empID;
        this.date = date;
        this.amount = amount;
    }

    public LecturerSalaryTM() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
