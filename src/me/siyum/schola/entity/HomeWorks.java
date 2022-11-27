package me.siyum.schola.entity;

import java.time.LocalDate;

public class HomeWorks implements SuperEntity {
    private String id;
    private String lecturerID;
    private LocalDate createDate;
    private LocalDate submissionDate;
    private String batch;
    private String message;

    public HomeWorks() {
    }

    public HomeWorks(String id, String lecturerID, LocalDate createDate, LocalDate submissionDate, String batch, String message) {
        this.id = id;
        this.lecturerID = lecturerID;
        this.createDate = createDate;
        this.submissionDate = submissionDate;
        this.batch = batch;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
