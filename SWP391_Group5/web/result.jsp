<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.Question" %>
<%
    String result = (String) request.getAttribute("result");
    Question question = (Question) request.getAttribute("question");
%>
<html>
<head>
    <title>Quiz Result</title>
</head>
<body>
<h2>Quiz Result</h2>
<h3><%= result %></h3>
<h3>Question: <%= question.getQuestion() %></h3>
<h3>Correct Answer: <%= question.getAlternatives()[question.getAnswer() - 1] %></h3>
<a href="quiz">Try Another Question</a>
</body>
</html>
