package me.siyum.schola.view.secretary.tm;

import com.jfoenix.controls.JFXButton;

import java.time.LocalDate;
import java.time.LocalTime;

public class SecretartClassesTM {
    private String id;
    private String subject;
    private String classRoom;
    private LocalDate date;
    private LocalTime time;
    private String batch;
    private JFXButton btn;

    public SecretartClassesTM() {
    }

    public SecretartClassesTM(String id, String subject, String classRoom, LocalDate date, LocalTime time, String batch, JFXButton btn) {
        this.id = id;
        this.subject = subject;
        this.classRoom = classRoom;
        this.date = date;
        this.time = time;
        this.batch = batch;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
