/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */


public class UserProgress {
    private int userId;
    private int courseId;
    private int grade;
    private double progress;
     private String completedExamIds;

    public UserProgress(int userId, int courseId, int grade, double progress) {
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
        this.progress = progress;
    }

    public UserProgress() {
    }

    public void setCompletedExamIds(String completedExamIds) {
        this.completedExamIds = completedExamIds;
    }

    public String getCompletedExamIds() {
        return completedExamIds;
    }

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getGrade() {
        return grade;
    }

    public double getProgress() {
        return progress;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }
}
