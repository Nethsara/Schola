package me.siyum.schola.dto;

import java.sql.Blob;
import java.time.LocalDate;

public class HomeWorkStudentsDTO {
    private String id;
    private String stID;
    private String name;
    private LocalDate dateSubmitted;
    private boolean status;
    private Blob file;

    public HomeWorkStudentsDTO() {
    }

    public HomeWorkStudentsDTO(String id, String stID, String name, LocalDate dateSubmitted, boolean status, Blob file) {
        this.id = id;
        this.stID = stID;
        this.name = name;
        this.dateSubmitted = dateSubmitted;
        this.status = status;
        this.file = file;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(LocalDate dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Blob getFile() {
        return file;
    }

    public void setFile(Blob file) {
        this.file = file;
    }
}
