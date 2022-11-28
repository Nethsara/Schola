package me.siyum.schola.dto;

public class LecturerScholaDTO {
    private String lecID;
    private int mark;

    public LecturerScholaDTO() {
    }

    public LecturerScholaDTO(String lecID, int mark) {
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
