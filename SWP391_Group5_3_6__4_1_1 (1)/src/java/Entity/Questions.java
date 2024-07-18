package Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Questions {
    List<Question> questions = new ArrayList<>();

    // Constructor that accepts a file path
    public Questions(String filePath) {
        loadQuestions(filePath);
    }

    public void loadQuestions(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            String line;
            String question = "";
            List<String> alternativesList = new ArrayList<>();
            int answer = 0;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                System.out.println("Read line: " + line); 

                if (line.isEmpty()) {
                    continue;
                }

                if (line.contains("?")) { 
                    question = line;
                    alternativesList.clear(); 
                    System.out.println("Question: " + question); 
                } else if (line.matches("\\d+\\).*")) { 
                    alternativesList.add(line);
                    System.out.println("Alternative: " + line); 
                } else if (line.startsWith("Answer:")) { 
                    answer = Integer.parseInt(line.split(":")[1].trim());
                    System.out.println("Answer: " + answer); 
                    // Add the question to the list
                    questions.add(new Question(question, alternativesList.toArray(new String[0]), answer));
                    System.out.println("Added question: " + question); 
                }
            }

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
