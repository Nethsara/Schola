package me.siyum.schola.dto;

import java.time.LocalDate;

public class SalaryDTO {
    private String id;
    private String empID;
    private LocalDate date;
    private double amount;
    private String method;


    public SalaryDTO() {
    }

    public SalaryDTO(String id, String empID, LocalDate date, double amount) {
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
