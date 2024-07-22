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
        <h1 class="mt-5">User Profile</h1>
        <%
            Users user = (Users) request.getAttribute("user");
            if (user != null) {
        %>
        <div class="card mt-3">
            <div class="card-body">
                <h5 class="card-title">Profile Details</h5>
                <p class="card-text"><strong>Username:</strong> <%= user.getUsername() %></p>
                <p class="card-text"><strong>Email:</strong> <%= user.getEmail() %></p>
                <p class="card-text"><strong>Full Name:</strong> <%= user.getFullName() %></p>
                <p class="card-text"><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
                <!-- Add more fields as necessary -->

                <!-- Display profile image if exists -->
                <%
                    if (user.getImage() != null && !user.getImage().isEmpty()) {
                %>
                <p class="card-text"><strong>Profile Image:</strong></p>
                <img src="<%= request.getContextPath() + "/" + user.getImage() %>" alt="Profile Image" class="img-thumbnail" style="max-width: 200px;">
                <%
                    }
                %>

                <!-- Form to upload a new profile image -->
                <h5 class="mt-4">Upload Profile Image</h5>
                <form action="UploadImage" method="post" enctype="multipart/form-data">
                    <div class="mb-3">
                        <label for="photo" class="form-label">Select Image</label>
                        <input type="file" class="form-control" id="photo" name="photo" required>
                    </div>
                    <input type="hidden" name="userId" value="<%= user.getUserId() %>">
                    <button type="submit" class="btn btn-primary">Upload Image</button>
                </form>
            </div>
        </div>
        <a href="home" class="btn btn-primary mt-3">Back to Home</a>
        <a href="EditProfile" class="btn btn-secondary mt-3">Edit Profile</a>
        <%
            } else {
        %>
        <p class="text-danger">User not found!</p>
        <%
            }
        %>
    </div>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="js/main.js"></script>
</body>
</html>
