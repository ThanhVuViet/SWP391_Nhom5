<%@ page import="Entity.PendingExpert" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pending Expert Detail</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Pending Expert Detail</h1>
        <%
            PendingExpert pendingExpert = (PendingExpert) request.getAttribute("pendingExpert");
            if (pendingExpert != null) {
        %>
        <table class="table table-bordered">
            <tr>
                <th>ID</th>
                <td><%= pendingExpert.getId() %></td>
            </tr>
            <tr>
                <th>Username</th>
                <td><%= pendingExpert.getUsername() %></td>
            </tr>
            <tr>
                <th>Email</th>
                <td><%= pendingExpert.getEmail() %></td>
            </tr>
            <tr>
                <th>Full Name</th>
                <td><%= pendingExpert.getFullName() %></td>
            </tr>
            <tr>
                <th>Birth Date</th>
                <td><%= pendingExpert.getBirthDate() %></td>
            </tr>
            <tr>
                <th>Description</th>
                <td><%= pendingExpert.getDescription() %></td>
            </tr>
            <tr>
                <th>Certification</th>
                <td><%= pendingExpert.getCertification() %></td>
            </tr>
            <!-- Add more fields as necessary -->
        </table>
        <a href="AdminApproveExpert?userId=<%= pendingExpert.getId() %>" class="btn btn-success">Approve</a>
        <a href="AdminRejectExpert?userId=<%= pendingExpert.getId() %>" class="btn btn-danger">Reject</a>
        <a href="pendingExperts.jsp" class="btn btn-secondary">Back</a>
        <%
            } else {
        %>
        <p>No expert found with the given ID.</p>
        <a href="pendingExperts.jsp" class="btn btn-secondary">Back</a>
        <%
            }
        %>
    </div>
</body>
</html>
