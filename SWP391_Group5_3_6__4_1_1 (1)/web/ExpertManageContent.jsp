<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Entity.Course" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>View All Courses for Content Upload - eLearning</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container mt-5">
        <h2>All Courses for Content Upload</h2>
        <div class="card mb-3">
            <div class="card-header">Course List</div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Course> courseList = (List<Course>) request.getAttribute("courseList");
                            for (Course course : courseList) {
                        %>
                        <tr>
                            <td><%= course.getCourseId() %></td>
                            <td><%= course.getTitle() %></td>
                            <td>
                                <a href="ContentUpload?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">Upload</a>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <a href="expertHome" class="btn btn-secondary">Back</a>
    </div>
</body>

</html>
