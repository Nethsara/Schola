package me.siyum.schola.view.students.tm;

import java.time.LocalDate;
import java.time.LocalTime;

public class StudentClassesTM {
    private String id;
    private LocalDate date;
    private LocalTime time;
    private String subject;
    private String lecturer;
    private String classRoom;
    private String status;

    public StudentClassesTM() {
    }

    public StudentClassesTM(String id, LocalDate date,
                            LocalTime time, String subject,
                            String lecturer, String classRoom,
                            String status) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.lecturer = lecturer;
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

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
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
