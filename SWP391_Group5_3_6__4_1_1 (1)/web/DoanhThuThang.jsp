<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Map" %>
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
        .bar {
            height: 20px;
            margin-bottom: 5px;
        }
    </style>
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <!-- Sidebar Start -->
            <div class="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark sidebar">
                <div class="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
                    <a href="adminDashboard" class="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <span class="fs-5 d-none d-sm-inline">Menu</span>
                    </a>
                     <ul class="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
                            <li class="nav-item">
                                <a href="adminDashboard" class="nav-link align-middle px-0">
                                    <i class="fs-4 bi-house"></i> <span class="ms-1 d-none d-sm-inline">Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="CourseList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-book"></i> <span class="ms-1 d-none d-sm-inline">Manage Course</span>
                                </a>
                            </li>
                            <li>
                                <a href="CustomerList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-people"></i> <span class="ms-1 d-none d-sm-inline">Customer Manage</span>
                                </a>
                            </li>
                            <li>
                                <a href="ExpertList" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-person-badge"></i> <span class="ms-1 d-none d-sm-inline">Expert Account Manage</span>
                                </a>
                            </li>
                           
                            <li>
                                <a href="DoanhThuThang.jsp" class="nav-link px-0 align-middle">
                                    <i class="fs-4 bi-calendar"></i> <span class="ms-1 d-none d-sm-inline">Monthly Revenue</span>
                                </a>
                            </li>
                            <li>
                                <a href="Top5User" class="nav-link px-0 align-middle">
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
                    <!-- Form chọn năm -->
                    <form method="post" action="OrderMonthly">
                        <div class="mb-3">
                            <label for="year" class="form-label">Chọn năm:</label>
                            <select id="year" name="selectedYear" class="form-select" required>
                                <option value="2023" name="selectedYear" >2023</option>
                                <option value="2024" name="selectedYear">2024</option>
                                <option value="2025"  name="selectedYear">2025</option>
                                <!-- Thêm các năm khác nếu cần -->
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Xem doanh thu</button>
                    </form>

                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Tháng</th>
                                <th>Doanh thu $</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                Map<Integer, Double> monthlyRevenue = (Map<Integer, Double>) request.getAttribute("monthlyRevenue");
                                String[] months = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};
                                double maxRevenue = 0;
                                if (monthlyRevenue != null) {
                                    for (Double revenue : monthlyRevenue.values()) {
                                        if (revenue > maxRevenue) {
                                            maxRevenue = revenue;
                                        }
                                    }
                                    for (Map.Entry<Integer, Double> entry : monthlyRevenue.entrySet()) {
                                        int month = entry.getKey();
                                        double revenue = entry.getValue();
                            %>
                            <tr>
                                <td><%= months[month - 1] %></td>
                                <td>
                                    <div class="bar" style="width: <%= (revenue / maxRevenue) * 100 %>%; background-color: #007bff;">
                                        <%= revenue %>
                                    </div>
                                </td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="2">No revenue data available.</td>
                            </tr>
                            <% } %>
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
