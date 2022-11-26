package me.siyum.schola.view.receptionist.tm;

import javafx.scene.control.CheckBox;

public class ReceptionistAttendanceMarkTM {
    private String id;
    private String name;
    private String stID;
    private String batch;
    private String phone;
    private String classRoom;
    private CheckBox actions;

    public ReceptionistAttendanceMarkTM(String id, String name, String stID, String batch, String phone, String classRoom, CheckBox actions) {
        this.id = id;
        this.name = name;
        this.batch = batch;
        this.phone = phone;
        this.classRoom = classRoom;
        this.actions = actions;
        this.stID = stID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public CheckBox getActions() {
        return actions;
    }

    public void setActions(CheckBox actions) {
        this.actions = actions;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }
}
