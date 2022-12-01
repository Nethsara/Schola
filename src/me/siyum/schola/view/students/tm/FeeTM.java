package me.siyum.schola.view.students.tm;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;

public class FeeTM {
    private String id;
    private LocalDate date;
    private double amount;
    private JFXButton btn;

    public FeeTM() {
    }

    public FeeTM(String id, LocalDate date, double amount, JFXButton btn) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
