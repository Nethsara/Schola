package me.siyum.schola.entity;

import java.time.LocalDate;

public class Attendance implements SuperEntity {
    private String id;
    private String classID;
    private LocalDate date;
    private int totalSt;
    private boolean status;


    public Attendance(String id, String classID, LocalDate date, int totalSt, boolean status) {
        this.id = id;
        this.classID = classID;
        this.date = date;
        this.totalSt = totalSt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalSt() {
        return totalSt;
    }

    public void setTotalSt(int totalSt) {
        this.totalSt = totalSt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
