<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <title>Upload Article</title>
</head>
<body>
    <div class="container" style="margin-top: 10px;">
        <div class="row" style="border: 1px darkgrey solid; border-radius: 10px; width: 50%; margin: 0 auto; padding: 20px;">
            <div class="col-sm-12">
                <h2 class="myClass">Upload Article</h2>
                <div style="margin-bottom: 20px;">
                    <p><strong>Section ID:</strong> <%= request.getParameter("sectionId") %></p>
                    <p><strong>Lecture ID:</strong> <%= request.getParameter("lectureId") %></p>
                    <p><strong>Section Title:</strong> <%= request.getParameter("sectionTitle") %></p>
                    <p><strong>Lecture Title:</strong> <%= request.getParameter("lectureTitle") %></p>
                    <p><strong>Course ID:</strong> <%= request.getParameter("courseId") %></p>
                </div>
                <form action="uploadArticle" method="post">
                    <!-- Hidden fields to carry over data -->
                    <input type="hidden" name="courseId" value="<%= request.getParameter("courseId") %>">
                    <input type="hidden" name="sectionId" value="<%= request.getParameter("sectionId") %>">
                    <input type="hidden" name="lectureId" value="<%= request.getParameter("lectureId") %>">
                    <input type="hidden" name="sectionTitle" value="<%= request.getParameter("sectionTitle") %>">
                    <input type="hidden" name="lectureTitle" value="<%= request.getParameter("lectureTitle") %>">

                    <div class="form-group">
                        <label>Title</label>
                        <input type="text" class="form-control" name="title" placeholder="Enter title" required>
                    </div>
                    <div class="form-group">
                        <label>Description</label>
                        <input type="text" class="form-control" name="description" placeholder="Enter description" required>
                    </div>
                    <div class="form-group">
                        <label>Article Link</label><br>
                        <input type="url" class="form-control" name="articleLink" placeholder="Enter article link" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
                <div style="margin-top: 20px;">
                    <%
                        String message = (String) request.getAttribute("message");
                        if (message != null && !message.isEmpty()) {
                            if (message.equals("Article link saved successfully.")) {
                                out.println("<div class='alert alert-success'>" + message + "</div>");
                            } else {
                                out.println("<div class='alert alert-danger'>" + message + "</div>");
                            }
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
