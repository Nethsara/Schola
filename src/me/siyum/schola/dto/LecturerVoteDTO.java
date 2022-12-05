package me.siyum.schola.dto;

import java.time.LocalDate;

public class LecturerVoteDTO {
    private String lecturer;
    private int vote;
    private LocalDate date;

    public LecturerVoteDTO() {
    }

    public LecturerVoteDTO(String lecturer, int vote, LocalDate date) {
        this.lecturer = lecturer;
        this.vote = vote;
        this.setDate(date);
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
