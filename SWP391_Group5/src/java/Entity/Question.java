/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Admin
 */
public class Question {
    private String question;
    private String[] alternatives;
    private int answer;

    public Question(String question, String[] alternatives, int answer) {
        this.question = question;
        this.alternatives = alternatives;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAlternatives() {
        return alternatives;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString() {
        String print = question + "\n";
        for (String alternative : alternatives) {
            print += alternative + "\n";
        }
        print += "Answer: " + answer + "\n";
        return print;
    }
}
