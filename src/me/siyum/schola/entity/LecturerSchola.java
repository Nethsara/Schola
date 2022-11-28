package me.siyum.schola.entity;

public class LecturerSchola implements SuperEntity {
    private String lecID;
    private int mark;

    public LecturerSchola() {
    }

    public LecturerSchola(String lecID, int mark) {
        this.lecID = lecID;
        this.mark = mark;
    }

    public String getLecID() {
        return lecID;
    }

    public void setLecID(String lecID) {
        this.lecID = lecID;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
