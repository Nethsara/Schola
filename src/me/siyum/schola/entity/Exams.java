package me.siyum.schola.entity;

import java.time.LocalDate;

public class Exams implements SuperEntity {
    private String id;
    private LocalDate date;
    private String batch;
    private String lecturer;

    public Exams() {
    }

    public Exams(String id, LocalDate date, String batch, String lecturer) {
        this.id = id;
        this.date = date;
        this.batch = batch;
        this.lecturer = lecturer;
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

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
