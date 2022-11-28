package me.siyum.schola.entity;

import java.time.LocalDate;

public class Notification implements SuperEntity {
    private String id;
    private String message;
    private LocalDate date;

    public Notification() {
    }

    public Notification(String id, String message, LocalDate date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
