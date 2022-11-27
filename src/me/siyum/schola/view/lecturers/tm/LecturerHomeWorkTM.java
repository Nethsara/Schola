package me.siyum.schola.view.lecturers.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class LecturerHomeWorkTM {
    private String id;
    private LocalDate createDate;
    private LocalDate submissionDate;
    private String batch;
    private Button btn;

    public LecturerHomeWorkTM() {
    }

    public LecturerHomeWorkTM(String id, LocalDate createDate, LocalDate submissionDate, String batch, Button btn) {
        this.id = id;
        this.createDate = createDate;
        this.submissionDate = submissionDate;
        this.batch = batch;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
