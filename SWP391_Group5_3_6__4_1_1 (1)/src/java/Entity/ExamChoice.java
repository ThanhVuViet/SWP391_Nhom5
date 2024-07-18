package Entity;

public class ExamChoice {
    private int choiceId;
    private int questionId;
    private String choiceText;
    private boolean isCorrect;

    public ExamChoice(int choiceId, String choiceText, boolean isCorrect) {
        this.choiceId = choiceId;
        this.choiceText = choiceText;
        this.isCorrect = isCorrect;
    }

    // Getters and setters
    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public ExamChoice() {
    }
    
}
