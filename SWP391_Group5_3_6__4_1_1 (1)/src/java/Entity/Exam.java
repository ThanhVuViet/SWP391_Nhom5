package Entity;

public class Exam {
    private int examId;
    private int courseId;
    private String title;
    private String description;
    private int lectureId;

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }

    public int getLectureId() {
        return lectureId;
    }

    // Getters and setters
    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
