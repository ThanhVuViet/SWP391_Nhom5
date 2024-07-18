<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="Entity.Course" %>
<%@ page import="Entity.Feedback" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>View All Feedback - eLearning</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
    <div class="container mt-5">
        <h2>All Feedback</h2>
        <div class="card mb-3">
            <div class="card-header">Feedback List</div>
            <div class="card-body">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Course Name</th>
                            <th>Feedback</th>
                            <th>Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Course> courseList = (List<Course>) request.getAttribute("courseList");
                            Map<Integer, List<Feedback>> courseFeedbackMap = (Map<Integer, List<Feedback>>) request.getAttribute("courseFeedback");
                            for (Course course : courseList) {
                                List<Feedback> feedbackList = courseFeedbackMap.get(course.getCourseId());
                                if (feedbackList != null) {
                                    for (Feedback feedback : feedbackList) {
                        %>
                        <tr>
                            <td><%= feedback.getFeedbackId() %></td>
                            <td><%= course.getTitle() %></td>
                            <td><%= feedback.getComment() %></td>
                            <td><%= feedback.getCreatedAt() %></td>
                            <td>
                                <a href="ContentUpload?courseId=<%= course.getCourseId() %>" class="btn btn-primary btn-sm">View</a>
                            </td>
                        </tr>
                        <%
                                    }
                                }
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
