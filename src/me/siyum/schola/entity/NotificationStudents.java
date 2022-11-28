package me.siyum.schola.entity;

public class NotificationStudents implements SuperEntity {
    private String notID;
    private String stID;
    private boolean status;

    public NotificationStudents() {
    }

    public NotificationStudents(String notID, String stID, boolean status) {
        this.notID = notID;
        this.stID = stID;
        this.status = status;
    }

    public String getNotID() {
        return notID;
    }

    public void setNotID(String notID) {
        this.notID = notID;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
