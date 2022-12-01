package me.siyum.schola.entity;

import java.sql.Blob;
import java.time.LocalDate;

public class Fee implements SuperEntity {
    private String id;
    private String stID;
    private double amount;
    private LocalDate date;
    private Blob bill;

    public Fee() {
    }

    public Fee(String id, String stID, double amount, LocalDate date, Blob bill) {
        this.id = id;
        this.stID = stID;
        this.amount = amount;
        this.date = date;
        this.bill = bill;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Blob getBill() {
        return bill;
    }

    public void setBill(Blob bill) {
        this.bill = bill;
    }
}
