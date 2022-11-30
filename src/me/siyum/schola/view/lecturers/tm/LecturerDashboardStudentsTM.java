package me.siyum.schola.view.lecturers.tm;

import com.jfoenix.controls.JFXButton;

public class LecturerDashboardStudentsTM {
    private String id;
    private String name;
    private int scholaMark;
    private JFXButton btn;

    public LecturerDashboardStudentsTM() {
    }

    public LecturerDashboardStudentsTM(String id, String name, int scholaMark, JFXButton btn) {
        this.id = id;
        this.name = name;
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

    public int getScholaMark() {
        return scholaMark;
    }

    public void setScholaMark(int scholaMark) {
        this.scholaMark = scholaMark;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}
