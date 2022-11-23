package me.siyum.schola.view.secretary.tm;

import java.time.LocalDate;

public class SecretarySalaryTM {
    private String id;
    private String to;
    private double amount;
    private LocalDate date;

    public SecretarySalaryTM(String id, String to, double amount, LocalDate date) {
        this.id = id;
        this.to = to;
        this.amount = amount;
        this.date = date;
    }

    public SecretarySalaryTM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
}
