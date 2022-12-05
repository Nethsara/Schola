package me.siyum.schola.view.secretary.tm;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;

public class SecretaryFeeTM {
    private String id;
    private String stID;
    private String name;
    private double amount;
    private LocalDate date;
    private JFXButton btn;

    public SecretaryFeeTM() {
    }

    public SecretaryFeeTM(String id, String stID, String name, double amount, LocalDate date, JFXButton btn) {
        this.id = id;
        this.stID = stID;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.btn = btn;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
