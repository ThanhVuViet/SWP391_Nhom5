<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Section" %>
<%@ page import="Entity.Lecture" %>
<%@ page import="Entity.Exam" %>
<%@ page import="Entity.Video" %>
<%@ page import="Entity.Article" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Learning Platform</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f9;
            color: #333;
        }

        header {
            background-color: #004d99;
            color: #fff;
            padding: 15px 0;
            text-align: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        main {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 20px;
            width: 100%;
        }

        .container {
            display: flex;
            flex-direction: column;
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        .video-section {
            flex: 2;
            padding: 20px;
            background-color: #fff;
            width: 100%;
        }

        .video-container, .article-container, .quiz-container {
            width: 100%;
            display: none; /* Hide content containers by default */
        }

        .content-container {
            display: none;
            flex-direction: column;
            align-items: center;
        }

        iframe {
            width: 80%;
            height: 450px;
            border-radius: 8px;
            border: none;
        }

        .course-content {
            flex: 1;
            background-color: #f1f1f1;
            padding: 20px;
            border-left: 2px solid #ddd;
        }

        .course-content h3 {
            margin-top: 0;
        }

        .course-content ul {
            list-style: none;
            padding: 0;
        }

        .course-content ul ul {
            padding-left: 20px;
        }

        .course-content li {
            margin-bottom: 10px;
        }

        .learning-tools {
            width: 100%;
            max-width: 1200px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            border-radius: 8px;
        }

        .learning-tools h3 {
            margin-top: 0;
        }

        .learning-tools button {
            margin: 10px;
            padding: 10px 20px;
            border: none;
            background-color: #004d99;
            color: #fff;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .learning-tools button:hover {
            background-color: #003366;
        }

        footer {
            background-color: #004d99;
            color: #fff;
            padding: 10px 0;
            text-align: center;
            position: fixed;
            width: 100%;
            bottom: 0;
            box-shadow: 0 -4px 8px rgba(0, 0, 0, 0.1);
        }

        a {
            color: #004d99;
            text-decoration: none;
            cursor: pointer;
        }

        a:hover {
            text-decoration: underline;
        }

        .content-details {
            width: 80%;
            margin: 10px 0;
        }

        .content-details p {
            text-align: center;
        }
    </style>
    <script>
        function showContent(contentId, contentLink, contentTitle, contentDescription) {
            // Hide all content containers
            var contentContainers = document.getElementsByClassName('content-container');
            for (var i = 0; i < contentContainers.length; i++) {
                contentContainers[i].style.display = 'none';
            }
            // Show the selected content container
            var contentContainer = document.getElementById(contentId);
            contentContainer.style.display = 'flex';

            // Set content details if the content is a video or article
            if (contentId === 'video-container') {
                document.getElementById('video-frame').src = 'https://www.youtube.com/embed/' + contentLink;
                document.getElementById('video-title').innerText = contentTitle;
                document.getElementById('video-description').innerText = contentDescription;
                document.getElementById('video-id').value = contentLink;
            } else if (contentId === 'article-container') {
                document.getElementById('article-frame').src = contentLink;
                document.getElementById('article-title').innerText = contentTitle;
                document.getElementById('article-description').innerText = contentDescription;
                document.getElementById('article-id').value = contentLink;
            }
        }

         function redirectToExam(examId) {
            var courseId = <%= request.getAttribute("courseId") %>;
            window.location.href = "Exam?id=" + examId + "&courseId=" + courseId;
        }
    </script>
</head>
<body>
    <header>
        <h1>Course: <%= ((Course) request.getAttribute("course")).getTitle() %></h1>
    </header>
    <main>
        <div class="container">
            <section class="video-section">
                <h2>Course Overview</h2>
                <p><%= ((Course) request.getAttribute("course")).getDescription() %></p>
                <div id="video-container" class="content-container video-container">
                    <strong id="video-title"></strong><br>
                    <iframe id="video-frame" src="" frameborder="0" allowfullscreen></iframe>
                    <div class="content-details">
                        <p id="video-description"></p>
                    </div>
                </div>
                <div id="article-container" class="content-container article-container">
                    <strong id="article-title"></strong><br>
                    <iframe id="article-frame" src="" frameborder="0"></iframe>
                    <div class="content-details">
                        <p id="article-description"></p>
                    </div>
                </div>
                <div id="quiz-container" class="content-container quiz-container">
                    <!-- Quiz content will be displayed here after redirection -->
                </div>
            </section>
            <aside class="course-content">
                <h3>Course Content</h3>
                <ul>
                    <%
                        List<Section> sections = (List<Section>) request.getAttribute("sections");
                        for (Section section : sections) {
                    %>
                        <li>Section: <%= section.getTitle() %>
                            <ul>
                                <%
                                    for (Lecture lecture : section.getLectures()) {
                                %>
                                    <li>Lecture: <%= lecture.getTitle() %>
                                        <ul>
                                            <li>Videos:
                                                <ul>
                                                    <%
                                                        for (Video v : lecture.getVideos()) {
                                                            String videoId = v.getYoutubeLink().split("v=")[1]; // Extract YouTube video ID
                                                            int ampersandPosition = videoId.indexOf('&');
                                                            if (ampersandPosition != -1) {
                                                                videoId = videoId.substring(0, ampersandPosition);
                                                            }
                                                    %>
                                                        <li>
                                                            <a href="javascript:void(0);" onclick="showContent('video-container', '<%= videoId %>', '<%= v.getTitle() %>', '<%= v.getDescription() %>')">
                                                                <%= v.getTitle() %>
                                                            </a>
                                                        </li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </li>
                                            <li>Articles:
                                                <ul>
                                                    <%
                                                        for (Article article : lecture.getArticles()) {
                                                    %>
                                                        <li>
                                                            <a href="javascript:void(0);" onclick="showContent('article-container', '<%= article.getArticleLink() %>', '<%= article.getTitle() %>', '<%= article.getDescription() %>')">
                                                                <%= article.getTitle() %>
                                                            </a>
                                                        </li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </li>
                                            <li>Exams:
                                                <ul>
                                                    <%
                                                        for (Exam exam : lecture.getExams()) {
                                                    %>
                                                        <li>
                                                            <a href="javascript:void(0);" onclick="redirectToExam('<%= exam.getExamId() %>')">
                                                                <%= exam.getTitle() %>
                                                            </a>
                                                        </li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </li>
                                        </ul>
                                    </li>
                                <%
                                    }
                                %>
                            </ul>
                        </li>
                    <%
                        }
                    %>
                </ul>
            </aside>
        </div>
        <section class="learning-tools">
            <h3>Schedule learning time</h3>
            <p>Learning a little each day adds up. Research shows that students who make learning a habit are more likely to reach their goals. Set time aside to learn and get reminders using your learning scheduler.</p>
            <button>Get started</button>
            <button>Dismiss</button>
        </section>
    </main>
    <footer>
        <p>&copy; 2024 Learning Platform</p>
    </footer>
</body>
</html>
