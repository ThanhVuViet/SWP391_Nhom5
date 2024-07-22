<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ExamQuestion" %>
<%@ page import="Entity.ExamChoice" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Exam Questions</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .question {
            margin: 20px;
            padding: 20px;
            border: 1px solid #ddd;
        }

        .choices {
            margin-top: 10px;
        }

        .choice {
            margin-bottom: 5px;
            display: flex;
            align-items: center;
        }

        .correct {
            font-weight: bold;
            color: green;
        }

        .edit-btn, .add-btn, .delete-btn {
            margin-left: 10px;
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            border-radius: 3px;
        }

        .edit-btn:hover, .add-btn:hover, .delete-btn:hover {
            background-color: #0056b3;
        }

        .add-btn {
            background-color: #28a745;
        }

        .delete-btn {
            background-color: #dc3545;
        }

        .add-question-btn {
            display: block;
            margin: 20px;
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            background-color: #28a745;
            border-radius: 5px;
        }

        .add-question-btn:hover {
            background-color: #218838;
        }

        .choices-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        .add-choice-form, .edit-choice-form, .add-question-form, .edit-question-form {
            display: none;
            margin-top: 10px;
        }

        .show {
            display: block;
        }
    </style>
    <script>
        function toggleAddChoiceForm(questionId) {
            const form = document.getElementById('add-choice-form-' + questionId);
            form.classList.toggle('show');
        }

        function toggleEditChoiceForm(choiceId) {
            const form = document.getElementById('edit-choice-form-' + choiceId);
            form.classList.toggle('show');
        }

        function toggleAddQuestionForm() {
            const form = document.getElementById('add-question-form');
            form.classList.toggle('show');
        }

        function toggleEditQuestionForm(questionId) {
            const form = document.getElementById('edit-question-form-' + questionId);
            form.classList.toggle('show');
        }
    </script>
</head>
<body>
    <h2>Exam Questions</h2>
    <!-- Add New Question Button -->
    <button class="add-question-btn" onclick="toggleAddQuestionForm()">Add New Question</button>

    <div id="add-question-form" class="add-question-form">
        <form action="AddQuestion" method="post">
            <label for="questionText">Question Text:</label>
            <input type="text" id="questionText" name="questionText">
            <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
            <input type="submit" value="Save">
        </form>
    </div>

    <!-- Iterate over questions -->
    <%
        List<ExamQuestion> questions = (List<ExamQuestion>) request.getAttribute("questions");
        int questionNumber = 1; // Initialize question number
        for (ExamQuestion question : questions) {
    %>
    <div class="question">
        <h3>
            Question <%= questionNumber++ %>: <%= question.getQuestionText() %>
            <button class="edit-btn" onclick="toggleEditQuestionForm(<%= question.getQuestionId() %>)">Edit</button>
               <a class="delete-btn" href="DeleteQuestion?questionId=<%= question.getQuestionId() %>&examId=<%= request.getParameter("examId") %>">Delete</a>
        </h3>
        <div id="edit-question-form-<%= question.getQuestionId() %>" class="edit-question-form">
            <form action="EditQuestion" method="post">
                <label for="questionText">Question Text:</label>
                <input type="text" id="questionText" name="questionText" value="<%= question.getQuestionText() %>">
                <input type="hidden" name="questionId" value="<%= question.getQuestionId() %>">
                <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
                <input type="submit" value="Save">
            </form>
        </div>
        <div class="choices-header">
            <button class="add-btn" onclick="toggleAddChoiceForm(<%= question.getQuestionId() %>)">Add New Choice</button>
        </div>
        <div class="choices">
            <!-- Iterate over choices -->
            <%
                List<ExamChoice> choices = question.getChoices();
                for (int i = 0; i < choices.size(); i++) {
                    ExamChoice choice = choices.get(i);
                    String choiceText = choice.getChoiceText().trim();                 
                    boolean hasNumber = choiceText.matches("^\\d+\\s*[\\).].*");
            %>
            <div class="choice <%= choice.isCorrect() ? "correct" : "" %>">
                <span><%= hasNumber ? "" : (i + 1) + ") " %><%= choiceText %> <% if (choice.isCorrect()) { %> (Correct) <% } %></span>
                <button class="edit-btn" onclick="toggleEditChoiceForm(<%= choice.getChoiceId() %>)">Edit</button>
                <a class="delete-btn" href="DeleteExamChoiceServlet?choiceId=<%= choice.getChoiceId() %>&examId=<%= request.getParameter("examId") %>">Delete</a>
            </div>
            <div id="edit-choice-form-<%= choice.getChoiceId() %>" class="edit-choice-form">
                <form action="EditExamChoice" method="post">
                    <label for="choiceText">Choice Text:</label>
                    <input type="text" id="choiceText" name="choiceText" value="<%= choice.getChoiceText() %>">
                    
                    <label for="isCorrect">Is Correct:</label>
                    <input type="checkbox" id="isCorrect" name="isCorrect" <%= choice.isCorrect() ? "checked" : "" %>>
                    
                    <input type="hidden" name="questionId" value="<%= question.getQuestionId() %>">
                    <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
                    <input type="hidden" name="choiceId" value="<%= choice.getChoiceId() %>">
                    
                    <input type="submit" value="Save">
                </form>
            </div>
            <%
                }
            %>
        </div>
        <div id="add-choice-form-<%= question.getQuestionId() %>" class="add-choice-form">
            <form action="AddExamChoice" method="post">
                <label for="choiceText">Choice Text:</label>
                <input type="text" id="choiceText" name="choiceText">
                
                <label for="isCorrect">Is Correct:</label>
                <input type="checkbox" id="isCorrect" name="isCorrect">
                
                <input type="hidden" name="questionId" value="<%= question.getQuestionId() %>">
                <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
                
                <input type="submit" value="Save">
            </form>
        </div>
    </div>
    <%
        }
    %>
</body>
</html>
