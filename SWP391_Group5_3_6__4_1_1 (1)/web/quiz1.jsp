<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Question" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
</head>
<body>
<h2>Quiz</h2>

<%
    List<Question> questions = (List<Question>) request.getAttribute("questions");
    String message = (String) request.getAttribute("message");

    if (questions != null && !questions.isEmpty()) {
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
%>
            <form action="quiz" method="post">
                <h3><%= question.getQuestion() %></h3>
                <%
                    String[] alternatives = question.getAlternatives();
                    for (int j = 0; j < alternatives.length; j++) {
                %>
                <input type="radio" name="answer" value="<%= j %>"><%= alternatives[j] %><br>
                <%
                    }
                %>
                <input type="hidden" name="questionIndex" value="<%= i %>">
                <input type="submit" value="Submit">
            </form>
<%
        }
    } else {
        out.println(message);
    }
%>
</body>
</html>
