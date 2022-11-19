package me.siyum.schola.dto;

public class TasksDTO {
    private String id;
    private String timeStamp;
    private String message;
    private Boolean status;

    public TasksDTO() {
    }

    public TasksDTO(String id, String timeStamp, String message, Boolean status) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.message = message;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
