<%@ page import="Entity.Users" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="mt-5">Edit Profile</h1>
        <%
            Users user = (Users) request.getAttribute("user");
            if (user != null) {
        %>
        <form action="EditProfile" method="post" class="mt-3">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" id="username" value="<%= user.getUsername() %>" readonly>
            </div>
            <div class="form-group mt-2">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
            </div>
            <div class="form-group mt-2">
                <label for="fullName">Full Name</label>
                <input type="text" class="form-control" id="fullName" name="fullName" value="<%= user.getFullName() %>">
            </div>
            <div class="form-group mt-2">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="<%= user.getPhoneNumber() %>">
            </div>
            <div class="form-group mt-2">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" value="<%= user.getAddress() %>">
            </div>
            <div class="form-group mt-2">
                <label for="birthDate">Birth Date</label>
                <input type="date" class="form-control" id="birthDate" name="birthDate" value="<%= user.getBirthDate() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(user.getBirthDate()) : "" %>">
            </div>
            <button type="submit" class="btn btn-primary mt-3">Save Changes</button>
            <a href="ViewProfile" class="btn btn-secondary mt-3">Cancel</a>
        </form>
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
