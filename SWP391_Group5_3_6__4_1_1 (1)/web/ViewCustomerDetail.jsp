<!DOCTYPE html>
<html lang="en">
<%@ page import="Entity.Users" %>
<head>
    <meta charset="UTF-8">
    <title>Customer Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Customer Details</h2>
        <div class="card">
            <div class="card-header">
                Customer Information
            </div>
            <div class="card-body">
                <%
                    Users user = (Users) request.getAttribute("user");
                    if (user != null) {
                %>
                <p><strong>ID:</strong> <%= user.getUserId() %></p>
                <p><strong>Name:</strong> <%= user.getUsername() %></p>
                <p><strong>Email:</strong> <%= user.getEmail() %></p>
                <p><strong>Phone:</strong> <%= user.getPhoneNumber() %></p>
                <p><strong>Address:</strong> <%= user.getAddress() %></p>
               
                <%
                    } else {
                %>
                <p>No customer details available.</p>
                <%
                    }
                %>
                <a href="CustomerList" class="btn btn-primary">Back to Customer List</a>
            </div>
        </div>
    </div>
</body>
</html>
