package me.siyum.schola.view.students.tm;

public class AnnouncementsTM {
    private String id;
    private String message;
    private String from;
    private String to;

    public AnnouncementsTM(String id) {
        this.id = id;
    }

    public AnnouncementsTM(String id, String message, String from, String to) {
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
