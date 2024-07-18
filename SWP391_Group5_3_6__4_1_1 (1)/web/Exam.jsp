<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.ExamQuestion" %>
<%@ page import="Entity.ExamChoice" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
            display: flex;
        }
        .content {
            flex: 1;
            margin-right: 220px; /* Adjusted for the sidebar */
        }
        .sidebar {
            width: 200px;
            background-color: #fff;
            padding: 15px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            position: fixed;
            right: 20px;
            top: 20px;
        }
        .question {
            background-color: #fff;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            transition: background-color 0.3s ease; /* Smooth transition when changing color */
        }
        .question.selected {
            background-color: #e0f7fa; /* Background color when question is selected */
        }
        .alternatives {
            margin-top: 10px;
        }
        .submit-btn {
            margin-top: 10px;
        }
        .sidebar button {
            display: block;
            width: 100%;
            padding: 10px;
            margin-bottom: 5px;
            background-color: #f0f0f0;
            border: none;
            cursor: pointer;
        }
        .sidebar button.selected {
            background-color: #b2dfdb; /* Background color when question in sidebar is selected */
        }
    </style>
</head>
<body>
<div class="content">
    <h2>Quiz</h2>

    <form id="quizForm" action="SubmitQuiz" method="post">
        <input type="hidden" name="examId" value="<%= request.getAttribute("examId") %>">
         <input type="hidden" name="courseId" value="<%= request.getAttribute("courseId") %>">
        <%
            List<ExamQuestion> questions = (List<ExamQuestion>) request.getAttribute("questions");
            if (questions != null) {
                for (int i = 0; i < questions.size(); i++) {
                    ExamQuestion question = questions.get(i);
        %>
        <div class="question" id="question_<%= i %>">
            <h3>Question <%= (i + 1) %>: <%= question.getQuestionText() %></h3>
            <div class="alternatives">
                <%
                    List<ExamChoice> choices = question.getChoices();
                    for (int j = 0; j < choices.size(); j++) {
                        ExamChoice choice = choices.get(j);
                %>
                <input type="radio" name="answer_<%= i %>" value="<%= choice.getChoiceId() %>" onclick="highlightQuestion(<%= i %>, this)" <%= request.getParameter("answer_" + i) != null && request.getParameter("answer_" + i).equals(String.valueOf(choice.getChoiceId())) ? "checked" : "" %>> <%= choice.getChoiceText() %><br>
                <%
                    }
                %>
            </div>
        </div>
        <%
                }
            } else {
        %>
        <p>No questions available for this exam.</p>
        <%
            }
        %>
        <input type="submit" value="Submit All" class="submit-btn">
    </form>
</div>

<div class="sidebar">
    <h3>Questions</h3>
    <%
        if (questions != null) {
            for (int i = 0; i < questions.size(); i++) {
    %>
    <button type="button" id="sidebarButton_<%= i %>" onclick="scrollToQuestion(<%= i %>)">Question <%= (i + 1) %></button>
    <%
            }
        }
    %>
</div>

<script>
    let selectedAnswers = {};

    function scrollToQuestion(index) {
        var questionElement = document.getElementById('question_' + index);
        if (questionElement) {
            questionElement.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
    }

    function highlightQuestion(index, input) {
        var questionElement = document.getElementById('question_' + index);
        var sidebarButton = document.getElementById('sidebarButton_' + index);

        // Store the selected answer
        selectedAnswers['answer_' + index] = input.value;

        // Remove 'selected' class from all questions and sidebar buttons
        document.querySelectorAll('.question').forEach(el => el.classList.remove('selected'));
        document.querySelectorAll('.sidebar button').forEach(el => el.classList.remove('selected'));

        // Add 'selected' class to the clicked question and sidebar button
        if (questionElement && sidebarButton) {
            questionElement.classList.add('selected');
            sidebarButton.classList.add('selected');
        }
    }

    window.onload = function() {
        // Highlight the questions that have been answered
        for (let key in selectedAnswers) {
            let index = key.split('_')[1];
            document.querySelector(`input[name="${key}"][value="${selectedAnswers[key]}"]`).checked = true;
            highlightQuestion(index, document.querySelector(`input[name="${key}"][value="${selectedAnswers[key]}"]`));
        }
    };
</script>

</body>
</html>
