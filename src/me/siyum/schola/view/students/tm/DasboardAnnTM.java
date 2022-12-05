package me.siyum.schola.view.students.tm;

import java.time.LocalDate;

public class DasboardAnnTM {
    private String id;
    private LocalDate date;
    private String message;
    private String from;

    public DasboardAnnTM() {
    }

    public DasboardAnnTM(String id, String message, String from) {
        this.id = id;
        this.date = date;
        this.message = message;
        this.from = from;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
