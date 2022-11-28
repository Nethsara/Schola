package me.siyum.schola.dto;

public class LecturerVoteDTO {
    private String lecturer;
    private int vote;

    public LecturerVoteDTO() {
    }

    public LecturerVoteDTO(String lecturer, int vote) {
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
