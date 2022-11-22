package me.siyum.schola.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Classes implements SuperEntity {
    private String id;
    private String subID;
    private String clssRoom;
    private LocalDate date;
    private LocalTime time;

    public Classes() {
    }

    public Classes(String id, String subID, String clssRoom, LocalDate date, LocalTime time) {
        this.id = id;
        this.subID = subID;
        this.clssRoom = clssRoom;
        this.date = date;
        this.time = time;
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
}
