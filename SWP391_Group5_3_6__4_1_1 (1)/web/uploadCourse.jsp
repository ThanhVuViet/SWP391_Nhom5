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
        <title>Content Selection</title>
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

            .content-options {
                display: flex;
                gap: 10px;
                justify-content: center;
                margin-top: 20px;
            }

            .content-option {
                display: flex;
                flex-direction: column;
                align-items: center;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                color: black;
                width: 100px;
            }

            .content-option span {
                margin-bottom: 5px;
            }

            .add-item {
                margin-top: 20px;
                padding: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .add-item:hover {
                background-color: #0056b3;
            }

            .add-lecture {
                margin-top: 10px;
                padding: 5px;
                background-color: #28a745;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .add-lecture:hover {
                background-color: #218838;
            }

            .delete-item {
                padding: 5px 10px;
                background-color: red;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .delete-item:hover {
                background-color: darkred;
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

            .uploaded-content {
                margin-top: 20px;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background-color: #f9f9f9;
            }

            .video-container, .article-container {
                margin-top: 20px;
            }

            .video-frame, .article-frame {
                width: 100%;
                height: 315px;
            }

            .delete-section, .delete-lecture {
                position: absolute;
                top: 10px;
                right: 10px;
                padding: 5px 10px;
                background-color: red;
                color: white;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            .delete-section:hover, .delete-lecture:hover {
                background-color: darkred;
            }
        </style>
        <script type="text/javascript">
            function confirmDeletion() {
                return confirm("Are you sure you want to delete this item? All data will be removed!");
            }

            function confirmSubmit() {
                return confirm("Are you sure you want to submit this course for approval?");
            }
        </script>
    </head>
    <body>
        <div id="curriculum-container">
            <h2>Upload Content for Course: <%= ((Course) request.getAttribute("course")).getTitle() %></h2>

            <!-- Add Section Form -->
            <form action="addSection" method="post">
                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                <label for="sectionTitle">Add New Section:</label>
                <input type="text" name="sectionTitle" placeholder="Section Title" required>
                <button type="submit" class="add-item">+ Add Section</button>
            </form>

            <!-- Iterate over sections -->
            <%
                List<Section> sections = (List<Section>) request.getAttribute("sections");
                int examId = (int) request.getAttribute("examId");
                for (Section section : sections) {
            %>
            <div class="section">
                <div class="section-title">
                    Section <span class="section-number"><%= section.getSectionId() %></span>: 
                    <input type="text" value="<%= section.getTitle() %>" readonly>
                    <form action="deleteSection" method="post" onsubmit="return confirmDeletion()" style="display: inline;">
                        <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                        <input type="hidden" name="sectionId" value="<%= section.getSectionId() %>">
                        <button type="submit" class="delete-section">Delete Section</button>
                    </form>
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
                        <div class="content-options">
                            <!-- Video content option -->
                            <form action="uploadVideo.jsp" method="post">
                                <input type="hidden" name="type" value="video">
                                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                <input type="hidden" name="sectionId" value="<%= section.getSectionId() %>">
                                <input type="hidden" name="lectureId" value="<%= lecture.getLectureId() %>">
                                <input type="hidden" name="sectionTitle" value="<%= section.getTitle() %>">
                                <input type="hidden" name="lectureTitle" value="<%= lecture.getTitle() %>">
                                <input type="hidden" name="examId" value="<%= examId %>">
                                <button type="submit" class="content-option">
                                    <span>Video</span>
                                </button>
                            </form>

                            <!-- Quiz content option -->
                            <form action="upload1.jsp" method="post">
                                <input type="hidden" name="type" value="quiz">
                                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                <input type="hidden" name="sectionId" value="<%= section.getSectionId() %>">
                                <input type="hidden" name="lectureId" value="<%= lecture.getLectureId() %>">
                                <input type="hidden" name="sectionTitle" value="<%= section.getTitle() %>">
                                <input type="hidden" name="lectureTitle" value="<%= lecture.getTitle() %>">
                                <input type="hidden" name="examId" value="<%= examId %>">
                                <button type="submit" class="content-option">
                                    <span>Quiz</span>
                                </button>
                            </form>

                            <!-- Article content option -->
                            <form action="uploadArticle.jsp" method="get">
                                <input type="hidden" name="type" value="article">
                                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                <input type="hidden" name="sectionId" value="<%= section.getSectionId() %>">
                                <input type="hidden" name="lectureId" value="<%= lecture.getLectureId() %>">
                                <input type="hidden" name="sectionTitle" value="<%= section.getTitle() %>">
                                <input type="hidden" name="lectureTitle" value="<%= lecture.getTitle() %>">
                                <input type="hidden" name="examId" value="<%= examId %>">
                                <button type="submit" class="content-option">
                                    <span>Article</span>
                                </button>
                            </form>
                        </div>
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
                                    <form action="DeleteExam" method="post" style="display: inline;" onsubmit="return confirmDeletion()">
                                        <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                        <input type="hidden" name="examId" value="<%= exam.getExamId() %>">
                                        <button type="submit" class="delete-item">Delete</button>
                                    </form>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                        <%
                            }
                        %>
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
                                        // Extract the YouTube video ID from the link
                                        String youtubeLink = video.getYoutubeLink();
                                        String videoId = youtubeLink.substring(youtubeLink.lastIndexOf("=") + 1);
                                %>
                                <li>
                                    <strong><%= video.getTitle() %></strong><br>
                                    <iframe class="video-frame" src="https://www.youtube.com/embed/<%= videoId %>" frameborder="0" allowfullscreen></iframe>
                                    <p><%= video.getDescription() %></p>
                                    <form action="DeleteVideo" method="post" style="display: inline;" onsubmit="return confirmDeletion()">
                                        <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                        <input type="hidden" name="videoId" value="<%= video.getVideoId() %>">
                                        <button type="submit" class="delete-item">Delete</button>
                                    </form>
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
                                    <iframe class="article-frame" src="<%= article.getArticleLink() %>" frameborder="0"></iframe>
                                    <p><%= article.getDescription() %></p>
                                    <form action="DeleteArticle" method="post" style="display: inline;" onsubmit="return confirmDeletion()">
                                        <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                                        <input type="hidden" name="articleId" value="<%= article.getArticleId() %>">
                                        <button type="submit" class="delete-item">Delete</button>
                                    </form>
                                </li>
                                <%
                                    }
                                %>
                            </ul>
                        </div>
                        <%
                            }
                        %>
                        <!-- Add form to delete lecture -->
                        <form action="deleteLecture" method="post" onsubmit="return confirmDeletion()" style="display: inline;">
                            <input type="hidden" name="lectureId" value="<%= lecture.getLectureId() %>">
                            <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                            <button type="submit" class="delete-lecture">Delete Lecture</button>
                        </form>
                    </div>
                    <%
                        }
                    %>
                </div>
                <!-- Add Lecture Form -->
                <form action="AddLecture" method="post">
                    <input type="hidden" name="sectionId" value="<%= section.getSectionId() %>">
                    <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                    <label for="lectureTitle">Add New Lecture:</label>
                    <input type="text" name="lectureTitle" placeholder="Lecture Title" required>
                    <button type="submit" class="add-lecture">+ Add Lecture</button>
                </form>
            </div>
            <%
                }
            %>

            <!-- Submit for Approval Form -->
            <form action="SubmitApproval" method="post" onsubmit="return confirmSubmit()">
                <input type="hidden" name="courseId" value="<%= ((Course) request.getAttribute("course")).getCourseId() %>">
                <button type="submit" class="add-item">Submit for Approval</button>
            </form>
        </div>
    </body>
</html>
