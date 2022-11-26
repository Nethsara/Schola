package me.siyum.schola.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClassesDTO {
    private String id;
    private String subID;
    private String clssRoom;
    private String batch;
    private LocalDate date;
    private LocalTime time;
    private String lecturer;

    public ClassesDTO() {
    }

    public ClassesDTO(String id, String subID, String lecturer, String clssRoom, String batch, LocalDate date, LocalTime time) {
        this.id = id;
        this.subID = subID;
        this.clssRoom = clssRoom;
        this.date = date;
        this.time = time;
        this.batch = batch;
        this.lecturer = lecturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public String getClssRoom() {
        return clssRoom;
    }

    public void setClssRoom(String clssRoom) {
        this.clssRoom = clssRoom;
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

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
