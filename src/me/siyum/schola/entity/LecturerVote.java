package me.siyum.schola.entity;

import java.time.LocalDate;

public class LecturerVote implements SuperEntity {
    private String lecturer;
    private int vote;
    private LocalDate date;

    public LecturerVote() {
    }

    public LecturerVote(String lecturer, int vote, LocalDate date) {
        this.lecturer = lecturer;
        this.vote = vote;
        this.date = date;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
