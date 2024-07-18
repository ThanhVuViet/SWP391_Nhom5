<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Result</title>
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
            width: 50%;
            margin: auto;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="result-container">
        <h2>Quiz Result</h2>
        
        <% 
            Double score = (Double) request.getAttribute("score");
            Integer correctAnswers = (Integer) request.getAttribute("correctAnswers");
            Integer totalQuestions = (Integer) request.getAttribute("totalQuestions");
            Double progress = (Double) request.getAttribute("progress");
        %>
        
        <p>Your score: <%= score != null ? score : 0 %>%</p>
        <p>Correct answers: <%= correctAnswers != null ? correctAnswers : 0 %> / <%= totalQuestions != null ? totalQuestions : 0 %></p>
        <p>Progress: <%= progress != null ? progress : 0 %>%</p>
        
        <%
            if (score != null && score >= 50) {
        %>
        <p style="color: green;">Pass</p>
        <%
            } else {
        %>
        <p style="color: red;">Fail</p>
        <%
            }
        %>
        
          <form action="Learning" method="get">
            <input type="hidden" name="courseId" value="<%= request.getAttribute("courseId") %>">
            <button type="submit">Back to Learning</button>
        </form>
        <form action="ViewResult" method="get">
            <input type="hidden" name="examId" value="<%= request.getAttribute("examId") %>">
            <button type="submit">View Result</button>
        </form>
    </div>
</body>
</html>
