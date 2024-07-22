/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.List;

public class ExamQuestion {
    private int questionId;
    private int examId;
    private String questionText;
    private List<ExamChoice> choices;

    public ExamQuestion(int questionId, String questionText) {
        this.questionId = questionId;
        this.questionText = questionText;
    }
    
    public void setChoices(List<ExamChoice> choices) {
        this.choices = choices;
    }

    public List<ExamChoice> getChoices() {
        return choices;
    }

    // Getters and setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ExamQuestion() {
    }
}

