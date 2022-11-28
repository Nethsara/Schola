package me.siyum.schola.entity;

public class LecturerVote implements SuperEntity {
    private String lecturer;
    private int vote;

    public LecturerVote() {
    }

    public LecturerVote(String lecturer, int vote) {
        this.lecturer = lecturer;
        this.vote = vote;
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
}
