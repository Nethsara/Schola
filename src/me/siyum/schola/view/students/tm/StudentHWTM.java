package me.siyum.schola.view.students.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class StudentHWTM {
    private String id;
    private String lecturer;
    private LocalDate createdDate;
    private LocalDate submissionDate;
    private Button btn;

    public StudentHWTM() {
    }

    public StudentHWTM(String id, String lecturer, LocalDate createdDate, LocalDate submissionDate, Button btn) {
        this.id = id;
        this.lecturer = lecturer;
        this.createdDate = createdDate;
        this.submissionDate = submissionDate;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
