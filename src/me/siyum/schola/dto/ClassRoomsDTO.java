package me.siyum.schola.dto;

public class ClassRoomsDTO {
    private String id;
    private int space;

    public ClassRoomsDTO() {
    }

    public ClassRoomsDTO(String id, int space) {
        this.id = id;
        this.space = space;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }
}
