package me.siyum.schola.dto;

import java.time.LocalDate;

public class SalaryDTO {
    private int id;
    private int empID;
    private LocalDate date;
    private double amount;
    private String method;

    public SalaryDTO(int id, int empID, LocalDate date, double amount, String method) {
        this.id = id;
        this.empID = empID;
        this.date = date;
        this.amount = amount;
        this.method = method;
    }

    public SalaryDTO() {
    }

    public SalaryDTO(int id, int empID, LocalDate date, double amount) {
        this.id = id;
        this.empID = empID;
        this.date = date;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
