package me.siyum.schola.dto;

public class AnnouncementsDTO {
    private String id;
    private String message;
    private String from;
    private String to;

    public AnnouncementsDTO(String id) {
        this.id = id;
    }

    public AnnouncementsDTO(String id, String message, String from, String to) {
        this.id = id;
        this.message = message;
        this.from = from;
        this.to = to;
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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
