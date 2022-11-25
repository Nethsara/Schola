package me.siyum.schola.dto;

public class StudentMarkDTO {
    private String id;
    private String exmID;
    private String sID;
    private double mark;

    public StudentMarkDTO() {
    }

    public StudentMarkDTO(String id, String exmID, String sID, double mark) {
        this.id = id;
        this.exmID = exmID;
        this.sID = sID;
        this.mark = mark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExmID() {
        return exmID;
    }

    public void setExmID(String exmID) {
        this.exmID = exmID;
    }

    public String getsID() {
        return sID;
    }

    public void setsID(String sID) {
        this.sID = sID;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
