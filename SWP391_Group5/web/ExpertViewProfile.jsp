<%@ page import="Entity.Expert" %>
<%@ page import="Entity.Users" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View Profile</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Profile Details</h1>
        <%
            Expert expert = (Expert) request.getAttribute("expertProfile");
            Users user = (Users) request.getAttribute("user");
            if (expert != null && user != null) {
        %>
        <div class="card mt-3">
            <div class="card-body">
                <h5 class="card-title">Profile Details</h5>
                <p class="card-text"><strong>Username:</strong> <%= user.getUsername() %></p>
                <p class="card-text"><strong>Email:</strong> <%= user.getEmail() %></p>
                <p class="card-text"><strong>Full Name:</strong> <%= user.getFullName() %></p>
                <p class="card-text"><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
                <p class="card-text"><strong>Description:</strong> <%= expert.getDescription() %></p>
                <p class="card-text"><strong>Certification:</strong> <%= expert.getCertification() %></p>
                <%
                    if (user.getImage() != null && !user.getImage().isEmpty()) {
                %>
                <p class="card-text"><strong>Profile Image:</strong></p>
                <img src="<%= user.getImage() %>" alt="Profile Image" class="img-thumbnail" style="max-width: 200px;">
                <%
                    }
                %>
            </div>
        </div>
        <a href="ExpertEditProfile?user_id=<%= user.getUserId() %>" class="btn btn-secondary mt-3">Edit Profile</a>
        <%
            } else {
        %>
        <p class="text-danger">Expert not found!</p>
        <%
            }
        %>
    </div>
</body>
</html>
