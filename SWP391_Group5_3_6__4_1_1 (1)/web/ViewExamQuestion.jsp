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
        }

        .correct {
            font-weight: bold;
            color: green;
        }
    </style>
</head>
<body>
    <h2>Exam Questions</h2>
    <!-- Iterate over questions -->
    <%
        List<ExamQuestion> questions = (List<ExamQuestion>) request.getAttribute("questions");
        for (ExamQuestion question : questions) {
    %>
    <div class="question">
        <h3>Question <%= question.getQuestionId() %>: <%= question.getQuestionText() %></h3>
        <div class="choices">
            <!-- Iterate over choices -->
            <%
                List<ExamChoice> choices = question.getChoices();
                for (ExamChoice choice : choices) {
            %>
            <div class="choice <%= choice.isCorrect() ? "correct" : "" %>">
                <%= choice.getChoiceText() %> <% if (choice.isCorrect()) { %> (Correct) <% } %>
            </div>
            <%
                }
            %>
        </div>
    </div>
    <%
        }
    %>
</body>
</html>
