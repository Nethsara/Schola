package me.siyum.schola.entity;

public class ClassRooms implements SuperEntity {
    private String id;
    private int space;

    public ClassRooms() {
    }

    public ClassRooms(String id, int space) {
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
