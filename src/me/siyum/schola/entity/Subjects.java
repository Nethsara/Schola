package me.siyum.schola.entity;

public class Subjects implements SuperEntity {
    private String subID;
    private String name;
    private String lecturer;

    public Subjects() {
    }

    public Subjects(String subID, String name, String lecturer) {
        this.subID = subID;
        this.name = name;
        this.lecturer = lecturer;
    }

    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
}
