package me.siyum.schola.dto;

import java.time.LocalDate;

public class NotificationsDTO {
    private String id;
    private String message;
    private LocalDate date;

    public NotificationsDTO() {
    }

    public NotificationsDTO(String id, String message, LocalDate date) {
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
