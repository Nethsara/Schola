package me.siyum.schola.view.lecturers.tm;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.time.LocalDate;

public class LecturerExamsTM {
    private String id;
    private LocalDate date;
    private String batch;
    private Button btn;

    public LecturerExamsTM() {
    }

    public LecturerExamsTM(String id, LocalDate date, String batch, Button btn) {
        this.id = id;
        this.date = date;
        this.batch = batch;
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
