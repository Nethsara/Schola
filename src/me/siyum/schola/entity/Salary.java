package me.siyum.schola.entity;

import java.time.LocalDate;

public class Salary implements SuperEntity {
    private String id;
    private String empID;
    private LocalDate date;
    private double amount;

    public Salary() {
    }

    public Salary(String id, String empID, LocalDate date, double amount) {
        this.id = id;
        this.empID = empID;
        this.date = date;
        this.amount = amount;
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
