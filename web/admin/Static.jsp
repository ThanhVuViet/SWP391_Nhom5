<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Entity.Order" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Revenue Statistics</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.0/css/bootstrap.min.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .sidebar {
            min-height: 100vh;
            background-color: #343a40;
            color: white;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
        }
        .sidebar .nav-link {
            color: white;
        }
        .sidebar .nav-link.active {
            background-color: #007bff;
        }
        .container-content {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            color: #007bff;
            font-weight: 700;
            margin-bottom: 30px;
        }
        .form-label {
            font-weight: 500;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #004085;
        }
        table {
            margin-top: 30px;
        }
        thead {
            background-color: #007bff;
            color: #ffffff;
        }
        th, td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar Start -->
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark sidebar">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="/" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                    <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="#" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="courseList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-book"></i> <span class="ms-1 d-none d-sm-inline">Manage Course</span>
                                </a>
                            </li>
                            <li>
                                <a href="CustomerList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Customer Manage</span>
                                </a>
                            </li>
                            <li>
                                <a href="expertList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-person-badge"></i> <span class="ms-1 d-none d-sm-inline">Expert Account Manage</span>
                                </a>
                            </li>
                            <li>
                                <a href="#viewReports" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-file-earmark-text"></i> <span class="ms-1 d-none d-sm-inline">View Reports</span>
                                </a>
                            </li>
                            <li>
                                <a href="#settings" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-gear"></i> <span class="ms-1 d-none d-sm-inline">Settings</span>
                                </a>
                            </li>
                            <li>
                                <a href="DoanhThuThang.jsp" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-calendar"></i> <span class="ms-1 d-none d-sm-inline">Monthly Revenue</span>
                                </a>
                            </li>
                            <li>
                                <a href="top5User" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Top 5 Customers</span>
                                </a>
                            </li>
                        </ul>
                    <hr>
                    <div class="dropdown pb-4">
                        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="dropdownUser1" data-bs-toggle="dropdown" aria-expanded="false">
                            <img src="https://github.com/mdo.png" alt="hugenerd" width="30" height="30" class="rounded-circle">
                            <span class="d-none d-sm-inline mx-1">Admin</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
                            <li><a class="dropdown-item" href="#">New project...</a></li>
                            <li><a class="dropdown-item" href="#">Settings</a></li>
                            <li><a class="dropdown-item" href="#">Profile</a></li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li><a class="dropdown-item" href="#">Sign out</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <!-- Sidebar End -->
            
            <!-- Main Content Start -->
           <main class="col py-3">
    <div class="container-content mt-5">
        <h2>Revenue Statistics</h2>
        <form method="post" action="orderFilter">
            <div class="mb-3">
                <label for="startDate" class="form-label">Start Date</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required
                       value="<%= request.getParameter("startDate") != null ? request.getParameter("startDate") : "" %>">
            </div>
            <div class="mb-3">
                <label for="endDate" class="form-label">End Date</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required
                       value="<%= request.getParameter("endDate") != null ? request.getParameter("endDate") : "" %>">
            </div>
            <button type="submit" class="btn btn-primary">Get Statistics</button>
        </form>

        <h3 class="mt-5">Results</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Order> orderList = (List<Order>) request.getAttribute("orderList");
                    if (orderList != null && !orderList.isEmpty()) {
                        for (Order order : orderList) {
                %>
                            <tr>
                                <td><%= order.getOrderId() %></td>
                                <td><%= order.getUserId() %></td>
                                <td><%= order.getOrderDate() %></td>
                                <td><%= order.getTotalAmount() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr>
                            <td colspan="4">No data available</td>
                        </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</main>

            <!-- Main Content End -->
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
