package me.siyum.schola.view.admin.tm;

import javafx.scene.control.Button;

public class AdminAnnouncmentTM {
    private String id;
    private String to;
    private String message;
    private Button btn;

    public AdminAnnouncmentTM() {
    }

    public AdminAnnouncmentTM(String id, String to, String message, Button btn) {
        this.id = id;
        this.to = to;
        this.message = message;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
