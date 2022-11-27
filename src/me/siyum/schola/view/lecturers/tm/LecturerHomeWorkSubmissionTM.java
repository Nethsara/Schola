package me.siyum.schola.view.lecturers.tm;

import javafx.scene.control.Button;

import java.time.LocalDate;

public class LecturerHomeWorkSubmissionTM {
    private String stID;
    private String stName;
    private LocalDate submittedDate;
    private boolean status;
    private Button viewBtn;
    private Button action;

    public LecturerHomeWorkSubmissionTM() {
    }

    public LecturerHomeWorkSubmissionTM(String stID, String stName, LocalDate submittedDate, boolean status, Button viewBtn, Button action) {
        this.stID = stID;
        this.stName = stName;
        this.submittedDate = submittedDate;
        this.status = status;
        this.viewBtn = viewBtn;
        this.action = action;
    }

    public String getStID() {
        return stID;
    }

    public void setStID(String stID) {
        this.stID = stID;
    }

    public String getStName() {
        return stName;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Button getViewBtn() {
        return viewBtn;
    }

    public void setViewBtn(Button viewBtn) {
        this.viewBtn = viewBtn;
    }

    public Button getAction() {
        return action;
    }

    public void setAction(Button action) {
        this.action = action;
    }
}
