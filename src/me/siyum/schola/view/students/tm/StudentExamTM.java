package me.siyum.schola.view.students.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class StudentExamTM {
    private String exmID;
    private LocalDate date;
    private String by;
    private String subject;
    private double marks;
    private String status;
    private Button btn;

    public StudentExamTM() {
    }

    public StudentExamTM(String exmID, LocalDate date, String by, String subject, double marks, String status, Button btn) {
        this.exmID = exmID;
        this.date = date;
        this.by = by;
        this.subject = subject;
        this.marks = marks;
        this.status = status;
        this.btn = btn;
    }

    public String getExmID() {
        return exmID;
    }

    public void setExmID(String exmID) {
        this.exmID = exmID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
