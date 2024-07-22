<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Entity.ExamChoice" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Add/Edit Exam Choice</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }

        form {
            display: flex;
            flex-direction: column;
            max-width: 400px;
        }

        label {
            margin: 10px 0 5px;
        }

        input[type="text"], input[type="checkbox"] {
            margin-bottom: 10px;
        }

        input[type="submit"] {
            padding: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <h2><%= request.getParameter("action").equals("edit") ? "Edit" : "Add" %> Exam Choice</h2>
    <%
        String action = request.getParameter("action");
        ExamChoice choice = null;
        if ("edit".equals(action)) {
            int choiceId = Integer.parseInt(request.getParameter("choiceId"));
            choice = (ExamChoice) request.getAttribute("choice");
        }
    %>
    <form action="addExamChoice" method="post">
        <label for="choiceText">Choice Text:</label>
        <input type="text" id="choiceText" name="choiceText" value="<%= choice != null ? choice.getChoiceText() : "" %>">
        
        <label for="isCorrect">Is Correct:</label>
        <input type="checkbox" id="isCorrect" name="isCorrect" <%= choice != null && choice.isCorrect() ? "checked" : "" %>>
        
        <input type="hidden" name="questionId" value="<%= request.getParameter("questionId") %>">
        <input type="hidden" name="examId" value="<%= request.getParameter("examId") %>">
        <% if (choice != null) { %>
        <input type="hidden" name="choiceId" value="<%= choice.getChoiceId() %>">
        <% } %>
        
        <input type="submit" value="Save">
    </form>
</body>
</html>
