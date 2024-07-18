<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Section" %>
<%@ page import="Entity.Lecture" %>
<%@ page import="Entity.Exam" %>
<%@ page import="Entity.Video" %>
<%@ page import="Entity.Article" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <title>Course Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .section {
            margin: 20px;
            position: relative;
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
        }

        .section-title {
            font-size: 1.5em;
            margin-bottom: 10px;
            display: flex;
            align-items: center;
        }

        .section-title input {
            font-size: 1.5em;
            margin-left: 10px;
            padding: 5px;
            flex: 1;
        }

        .lecture {
            background-color: #f9f9f9;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
            position: relative;
        }

        .lecture input[type="text"] {
            font-weight: bold;
            font-size: 1.2em;
            margin-left: 10px;
            padding: 5px;
            flex: 1;
        }

        .uploaded-content {
            margin-top: 20px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        
        .view-questions {
            margin-top: 10px;
            padding: 5px;
            background-color: #ffc107;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .view-questions:hover {
            background-color: #e0a800;
        }

        .video-container, .article-container {
            margin-top: 20px;
        }

        .video-frame {
            width: 100%;
            height: 400px;
        }

        .action-buttons {
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
        }

        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.2em;
            color: white;
        }

        .btn-approve {
            background-color: #28a745;
        }

        .btn-approve:hover {
            background-color: #218838;
        }

        .btn-reject {
            background-color: #dc3545;
        }

        .btn-reject:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div id="curriculum-container">
        <h2>Course Details: <%= ((Course) request.getAttribute("course")).getTitle() %></h2>

        <!-- Iterate over sections -->
        <%
            List<Section> sections = (List<Section>) request.getAttribute("sections");
            for (Section section : sections) {
        %>
        <div class="section">
            <div class="section-title">
                Section <span class="section-number"><%= section.getSectionId() %></span>: 
                <input type="text" value="<%= section.getTitle() %>" readonly>
            </div>
            <div class="lectures-container">
                <!-- Iterate over lectures -->
                <%
                    List<Lecture> lectures = section.getLectures();
                    for (Lecture lecture : lectures) {
                %>
                <div class="lecture">
                    <label>Lecture <span class="lecture-number"><%= lecture.getLectureId() %></span>:</label>
                    <input type="text" value="<%= lecture.getTitle() %>" readonly>

                    <!-- Display videos if available -->
                    <%
                        List<Video> videos = lecture.getVideos();
                        if (videos != null && !videos.isEmpty()) {
                    %>
                    <div class="video-container">
                        <h4>Uploaded Videos:</h4>
                        <ul>
                            <%
                                for (Video video : videos) {
                                    String youtubeLink = video.getYoutubeLink();
                                    String videoId = youtubeLink.substring(youtubeLink.lastIndexOf("=") + 1);
                            %>
                            <li>
                                <strong><%= video.getTitle() %></strong><br>
                                <iframe class="video-frame" src="https://www.youtube.com/embed/<%= videoId %>" frameborder="0" allowfullscreen></iframe>
                                <p><%= video.getDescription() %></p>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <%
                        }
                    %>

                    <!-- Display articles if available -->
                    <%
                        List<Article> articles = lecture.getArticles();
                        if (articles != null && !articles.isEmpty()) {
                    %>
                    <div class="article-container">
                        <h4>Uploaded Articles:</h4>
                        <ul>
                            <%
                                for (Article article : articles) {
                            %>
                            <li>
                                <strong><%= article.getTitle() %></strong><br>
                                <iframe src="<%= article.getArticleLink() %>" width="100%" height="400px"></iframe>
                                <p><%= article.getDescription() %></p>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <%
                        }
                    %>

                    <!-- Display exams if available -->
                    <%
                        List<Exam> exams = lecture.getExams();
                        if (exams != null && !exams.isEmpty()) {
                    %>
                    <div class="exams-container">
                        <h4>Exams:</h4>
                        <ul>
                            <%
                                for (Exam exam : exams) {
                            %>
                            <li>
                                <%= exam.getTitle() %> - <%= exam.getDescription() %>
                                <a href="ViewExamQuestionsServlet?examId=<%= exam.getExamId() %>" class="view-questions button-link">
                                    View Questions
                                </a>
                            </li>
                            <%
                                }
                            %>
                        </ul>
                    </div>
                    <%
                        }
                    %>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>

        <!-- Approve/Reject buttons -->
        <div class="action-buttons">
            <form action="ApproveCourse" method="post">
                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                <button type="submit" class="btn btn-approve">Approve</button>
            </form>

            <form action="RejectCourse" method="post">
                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                <button type="submit" class="btn btn-reject">Reject</button>
            </form>
        </div>
    </div>
</body>
</html>
