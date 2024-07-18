<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ExamResult" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }
        .result-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            width: 70%;
            margin: auto;
        }
        .question {
            margin-bottom: 15px;
        }
        .correct {
            color: green;
        }
        .incorrect {
            color: red;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <h2>Exam Results</h2>
        <% 
            List<ExamResult> examResults = (List<ExamResult>) request.getAttribute("examResults");
            if (examResults != null) {
                for (ExamResult result : examResults) {
        %>
        <div class="question">
            <p><strong>Question:</strong> <%= result.getQuestionText() %></p>
            <p><strong>Your Answer:</strong> <%= result.getChoiceText() %></p>
            <p class="<%= result.isCorrect() ? "correct" : "incorrect" %>">
                <%= result.isCorrect() ? "Correct" : "Incorrect" %>
            </p>
        </div>
        <% 
                }
            } else {
        %>
        <p>No results found.</p>
        <% 
            }
        %>
    </div>
</body>
</html>
