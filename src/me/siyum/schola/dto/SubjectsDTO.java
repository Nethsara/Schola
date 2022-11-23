package me.siyum.schola.dto;

public class SubjectsDTO {
    private String subID;
    private String name;
    private String lecturer;

    public SubjectsDTO() {
    }

    public SubjectsDTO(String subID, String name, String lecturer) {
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
