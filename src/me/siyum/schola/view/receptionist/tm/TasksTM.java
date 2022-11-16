package me.siyum.schola.view.receptionist.tm;

import javafx.scene.control.Button;

public class TasksTM {
    private String id;
    private String time;
    private boolean status;
    private Button cancel;
    private String message;

    public TasksTM() {
    }

    public TasksTM(String id, String time, String message, boolean status, Button cancel) {
        this.id = id;
        this.time = time;
        this.status = status;
        this.cancel = cancel;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
