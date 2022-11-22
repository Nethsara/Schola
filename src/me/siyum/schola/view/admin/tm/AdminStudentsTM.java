package me.siyum.schola.view.admin.tm;

import javafx.scene.control.Button;

import java.sql.Blob;

public class AdminStudentsTM {
    private String id;
    private Blob image;
    private String name;
    private String email;
    private String nic;
    private int scholaMarks;
    private Boolean status;
    private Button btn;

    public AdminStudentsTM() {
    }

    public AdminStudentsTM(String id, Blob image, String name, String email,
                               String nic, int scholaMarks, Boolean status, Button btn) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.scholaMarks = scholaMarks;
        this.status = status;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getScholaMarks() {
        return scholaMarks;
    }

    public void setScholaMarks(int scholaMarks) {
        this.scholaMarks = scholaMarks;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
