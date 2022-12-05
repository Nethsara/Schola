package me.siyum.schola.view.secretary.tm;

import javafx.scene.control.Button;

public class SecretaryDashboardStTM {
    private String id;
    private String name;
    private String email;
    private int scholaMark;
    private Button btn;

    public SecretaryDashboardStTM() {
    }

    public SecretaryDashboardStTM(String id, String name, String email, int scholaMark, Button btn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.scholaMark = scholaMark;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScholaMark() {
        return scholaMark;
    }

    public void setScholaMark(int scholaMark) {
        this.scholaMark = scholaMark;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
