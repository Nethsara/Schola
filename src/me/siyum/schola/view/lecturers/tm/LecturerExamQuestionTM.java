package me.siyum.schola.view.lecturers.tm;

import javafx.scene.control.Button;

public class LecturerExamQuestionTM {
    private int questionNo;
    private String exmID;
    private String question;
    private String mcq1;
    private String mcq2;
    private String mcq3;
    private String mcq4;
    private int correctAns;
    private Button btn;

    public LecturerExamQuestionTM() {
    }

    public LecturerExamQuestionTM(int questionNo, String exmID, String question, String mcq1, String mcq2, String mcq3, String mcq4, int correctAns, Button btn) {
        this.questionNo = questionNo;
        this.exmID = exmID;
        this.question = question;
        this.mcq1 = mcq1;
        this.mcq2 = mcq2;
        this.mcq3 = mcq3;
        this.mcq4 = mcq4;
        this.correctAns = correctAns;
        this.setBtn(btn);
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public String getExmID() {
        return exmID;
    }

    public void setExmID(String exmID) {
        this.exmID = exmID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getMcq1() {
        return mcq1;
    }

    public void setMcq1(String mcq1) {
        this.mcq1 = mcq1;
    }

    public String getMcq2() {
        return mcq2;
    }

    public void setMcq2(String mcq2) {
        this.mcq2 = mcq2;
    }

    public String getMcq3() {
        return mcq3;
    }

    public void setMcq3(String mcq3) {
        this.mcq3 = mcq3;
    }

    public String getMcq4() {
        return mcq4;
    }

    public void setMcq4(String mcq4) {
        this.mcq4 = mcq4;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
