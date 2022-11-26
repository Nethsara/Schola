package me.siyum.schola.view.lecturers.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;
import java.time.LocalTime;

public class LecturerAttendanceTM {
    private String id;
    private String attendanceID;
    private String batch;
    private LocalDate date;
    private LocalTime time;
    private String classRoom;
    private String lecturer;
    private Button btn;

    public LecturerAttendanceTM() {
    }

    public LecturerAttendanceTM(String id, String attendanceID, String batch, LocalDate date, LocalTime time, String classRoom, String lecturer, Button btn) {
        this.id = id;
        this.attendanceID = attendanceID;
        this.batch = batch;
        this.date = date;
        this.time = time;
        this.classRoom = classRoom;
        this.lecturer = lecturer;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public String getAttendanceID() {
        return attendanceID;
    }

    public void setAttendanceID(String attendanceID) {
        this.attendanceID = attendanceID;
    }
}
