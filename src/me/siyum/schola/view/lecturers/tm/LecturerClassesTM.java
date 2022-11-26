package me.siyum.schola.view.lecturers.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class LecturerClassesTM {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String subject;
    private String batch;
    private String classRoom;
    private String status;

    public LecturerClassesTM() {
    }

    public LecturerClassesTM(String id, LocalDate date, LocalTime time, String subject, String batch, String classRoom, String status) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.batch = batch;
        this.classRoom = classRoom;
        this.status = status;
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
