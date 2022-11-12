package me.siyum.schola.entity;

public class Tasks implements SuperEntity {
    private int id;
    private String timeStamp;
    private String message;
    private Boolean status;

    public Tasks() {
    }

    public Tasks(int id, String timeStamp, String message, Boolean status) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = message;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
