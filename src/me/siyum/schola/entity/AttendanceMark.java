package me.siyum.schola.entity;

public class AttendanceMark implements SuperEntity {
    private String amID;
    private String stID;
    private boolean isPresent;

    public AttendanceMark() {
    }

    public AttendanceMark(String amID, String stID, boolean isPresent) {
        this.amID = amID;
        this.stID = stID;
        this.isPresent = isPresent;
    }

    public String getAmID() {
        return amID;
    }

    public void setAmID(String amID) {
        this.amID = amID;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }
}
