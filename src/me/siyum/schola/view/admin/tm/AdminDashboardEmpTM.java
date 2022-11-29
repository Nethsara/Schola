package me.siyum.schola.view.admin.tm;

import javafx.scene.shape.Circle;

public class AdminDashboardEmpTM {
    private String id;
    private Circle circle;
    private String name;
    private int scholaMark;

    public AdminDashboardEmpTM() {
    }

    public AdminDashboardEmpTM(String id, Circle circle, String name, int scholaMark) {
        this.id = id;
        this.circle = circle;
        this.name = name;
        this.scholaMark = scholaMark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
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
}
