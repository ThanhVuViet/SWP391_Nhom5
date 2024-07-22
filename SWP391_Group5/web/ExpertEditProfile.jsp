<%@ page import="Entity.Expert" %>
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
            Expert expert = (Expert) request.getAttribute("expert");
            if (user != null && expert != null) {
        %>
        <form action="ExpertEditProfile" method="post" enctype="multipart/form-data">
            <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
            <div class="mb-3">
                <label for="username" class="form-label">Username</label>
                <input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>" readonly>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>">
            </div>
            <div class="mb-3">
                <label for="full_name" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="full_name" name="full_name" value="<%= user.getFullName() %>">
            </div>
            <div class="mb-3">
                <label for="phone_number" class="form-label">Phone Number</label>
                <input type="text" class="form-control" id="phone_number" name="phone_number" value="<%= user.getPhoneNumber() %>">
            </div>
            <div class="mb-3">
                <label for="address" class="form-label">Address</label>
                <input type="text" class="form-control" id="address" name="address" value="<%= user.getAddress() %>">
            </div>
            <div class="mb-3">
                <label for="birth_date" class="form-label">Birth Date</label>
                <input type="date" class="form-control" id="birth_date" name="birth_date" value="<%= user.getBirthDate() != null ? user.getBirthDate().toString() : "" %>">
            </div>
            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description"><%= expert.getDescription() %></textarea>
            </div>
            <div class="mb-3">
                <label for="certification" class="form-label">Certification</label>
                <textarea class="form-control" id="certification" name="certification"><%= expert.getCertification() %></textarea>
            </div>
            <div class="mb-3">
                <label for="profile_image" class="form-label">Profile Image</label>
                <input type="file" class="form-control" id="profile_image" name="profile_image">
            </div>
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>
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
